package br.com.alura.escolalura.service;

import br.com.alura.escolalura.entity.Course;

import java.util.List;

public interface CourseService {

    Course save(Course course);
    List<Course> getAll();

}
