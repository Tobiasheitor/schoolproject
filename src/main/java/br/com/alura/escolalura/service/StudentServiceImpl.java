package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelStudent;
import br.com.alura.escolalura.dto.SubjectDTO;
import br.com.alura.escolalura.dto.SubjectStudentNotesDTO;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.entity.Subject;
import br.com.alura.escolalura.repository.CourseRepository;
import br.com.alura.escolalura.repository.StudentRepository;
import br.com.alura.escolalura.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private SubjectRepository subjectRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Student save(ModelStudent modelStudent) {
    log.info("Service start - save student: {}", modelStudent);
    List<SubjectDTO> subjectDTOList = new ArrayList<>();
    Student studentTemp = modelMapper.map(modelStudent, Student.class);

    Course course = courseRepository.findById(modelStudent.getCourseId()).get();

    Iterable<Subject> subjects = subjectRepository.findAllById(course.getSubjects());
    subjects.forEach(sub -> subjectDTOList.add(modelMapper.map(sub, SubjectDTO.class)));
    studentTemp.setSubjects(subjectDTOList);

    Student studentSaved = studentRepository.save(studentTemp);

    course.getStudents().add(studentSaved.getId());
    courseRepository.save(course);

    return studentSaved;
  }

  @Override
  public List<SubjectStudentNotesDTO> getStudentSubjects(String studentId) {
    List<SubjectStudentNotesDTO> studentNotes = new ArrayList<>();

    Student student = studentRepository.findById(new ObjectId(studentId)).get();

    if (student == null) {
      log.error("StudentServiceImpl.getStudentSubjects - Student Not Found");
      return studentNotes;
    }

    student.getSubjects().forEach(subject -> {
      Optional<Subject> subjectRepo = subjectRepository.findById(subject.getSubjectId());
      studentNotes.add(new SubjectStudentNotesDTO(subjectRepo.get().getName(), subject.getNotes()));
    });

    log.info("StudentServiceImpl.getStudentSubjects - End - notes: {}", studentNotes);
    return studentNotes;
  }

}
