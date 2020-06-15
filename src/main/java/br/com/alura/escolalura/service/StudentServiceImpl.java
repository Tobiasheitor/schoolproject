package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelStudent;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.repository.CourseRepository;
import br.com.alura.escolalura.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Student save(ModelStudent modelStudent) {
    log.info("Service start - save student: {}", modelStudent);

    Student studentSaved = studentRepository.save(modelMapper.map(modelStudent, Student.class));

    Course course = courseRepository.findOne(modelStudent.getCourseId());
    course.getStudents().add(studentSaved.getId());
    courseRepository.save(course);

    return studentSaved;
  }

}
