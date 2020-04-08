package br.com.alura.escolalura.service;

import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course save(Course course) {
        log.info("Service start - save course: {}", course);
        Course result = null;

        result = courseRepository.save(course);

        log.debug("Service end - saved course: {}", result);
        return result;
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

}
