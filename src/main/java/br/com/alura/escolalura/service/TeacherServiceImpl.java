package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelTeacher;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Teacher;
import br.com.alura.escolalura.repository.CourseRepository;
import br.com.alura.escolalura.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Teacher saveTeacher(ModelTeacher teacher) {
        log.info("TeacherServiceImpl.saveTeacher Start - save teacher: {}", teacher);

        Teacher savedTeacher = teacherRepository.save(new Teacher(null, teacher.getName(), teacher.getAddress()));

        Course course = courseRepository.findById(teacher.getCourseId()).get();
        course.getTeachers().add(savedTeacher.getId());

        courseRepository.save(course);

        return savedTeacher;
    }
}
