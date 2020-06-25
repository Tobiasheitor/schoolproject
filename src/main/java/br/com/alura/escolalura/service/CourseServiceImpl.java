package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ModelCourse;
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
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Course save(ModelCourse course) {
        log.info("Service start - save course: {}", course);
        List<Subject> subjects = new ArrayList<>();
        Course result;

        course.getSubject().stream().filter(modelSubject -> modelSubject.getSubjectName() != "")
                .forEach(modelSubject -> subjects.add(new Subject(null, modelSubject.getSubjectName(), modelSubject.getDay())));

        List<Subject> savedSubjects = subjectRepository.saveAll(subjects);
        List<ObjectId> subjectIds = savedSubjects.stream().map(s -> s.getSubjectId()).collect(Collectors.toList());

        Course mappedCourse = modelMapper.map(course, Course.class);
        mappedCourse.setSubjects(subjectIds);

        result = courseRepository.save(mappedCourse);

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

        Optional<Course> optionalCourse = courseRepository.findById(new ObjectId(courseId));

        List<ObjectId> studentIds = optionalCourse.get().getStudents();
        studentIds.forEach(objectId -> {
            students.add(studentRepository.findById(objectId).get());
        });

        log.info("CourseServiceImpl.getCourseStudents end - students: {}", students);
        return students;
    }

}
