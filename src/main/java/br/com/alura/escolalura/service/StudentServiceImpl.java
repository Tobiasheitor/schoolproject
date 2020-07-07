package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.*;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Student studentTemp = new Student(null, modelStudent.getName(), modelStudent.getBirthDate(), null, new ContactDTO(modelStudent.getContact().getAddress()));

        Course course = courseRepository.findById(modelStudent.getCourseId()).get();

        Iterable<Subject> subjects = subjectRepository.findAllById(course.getSubjects());
        subjects.forEach(sub -> {
            SubjectDTO mapped = modelMapper.map(sub, SubjectDTO.class);
            mapped.setNotes(Arrays.asList("", "", ""));
            subjectDTOList.add(mapped);
        });
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

    @Override
    public void saveNotes(CreateUserDTO createUserDTO) {

        Student student = studentRepository.findById(new ObjectId(createUserDTO.getStudent())).get();

        createUserDTO.getSubjects().forEach(sub -> {
            Subject subject = findSubjectByName(sub);

            List<SubjectDTO> collect = student.getSubjects().stream().map(s -> {
                if (s.getSubjectId().equals(subject.getSubjectId()))
                    s.setNotes(getTheFirstElements(createUserDTO.getNotes()));
                return s;
            }).collect(Collectors.toList());

            student.setSubjects(collect);

        });

        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> allStudents = studentRepository.findAll();

        return allStudents;
    }

    @Override
    public Student getStudent(String id) {

        Optional<Student> student = studentRepository.findById(new ObjectId(id));

        return student.get();
    }

    @Override
    public void editStudent(EditStudent editStudent) {
        Student student = studentRepository.findById(new ObjectId(editStudent.getStudentId())).get();
        student.setName(editStudent.getName());
        student.setBirthDate(editStudent.getBirthDate());
        student.setContact(new ContactDTO(editStudent.getAddress()));

        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String id) {
        List<Course> allCourses = courseRepository.findAll();

        Student student = studentRepository.findById(new ObjectId(id)).get();
        Course course = allCourses.stream().filter(c -> c.getStudents().contains(student.getId())).findFirst().get();

        List<ObjectId> courseStudents = course.getStudents();
        courseStudents.remove(student.getId());

        course.setStudents(courseStudents);

        courseRepository.save(course);
        studentRepository.delete(student);
    }

    private Subject findSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }

    private List<String> getTheFirstElements(List<String> items) {
        List<String> response = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            response.add(items.remove(0));
        }

        return response;
    }

}
