package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelCourse;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.repository.CourseRepository;
import br.com.alura.escolalura.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Course save(ModelCourse course) {
        log.info("Service start - save course: {}", course);
        Course result;

        result = courseRepository.save(modelMapper.map(course, Course.class));

        log.debug("Service end - saved course: {}", result);
        return result;
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Student> getCourseStudents(String courseId) {
        List<Student> students = new ArrayList<>();

        Course foundCourse = courseRepository.findOne(new ObjectId(courseId));
        List<ObjectId> studentIds = foundCourse.getStudents();
        studentIds.forEach(objectId -> {
            students.add(studentRepository.findOne(objectId));
        });

        return students;
    }

}
