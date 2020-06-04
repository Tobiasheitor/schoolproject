package br.com.alura.escolalura.service;

import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void save(Student student) {
        log.info("Service start - save student: {}", student);

        Course course = courseRepository.findByName(student.getCourse());
        course.getStudents().add(student);

        courseRepository.save(course);
    }

}
