package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelCourse;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import java.util.List;

public interface CourseService {

    Course save(ModelCourse course);

    List<Course> getAll();

    List<Student> getCourseStudents(String courseId);

}
