package br.com.alura.escolalura.service;

import br.com.alura.escolalura.dto.ClosureDTO;
import br.com.alura.escolalura.dto.SubjectDTO;
import br.com.alura.escolalura.dto.SubjectStudentInfoDTO;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.entity.Subject;
import br.com.alura.escolalura.repository.CourseRepository;
import br.com.alura.escolalura.repository.StudentRepository;
import br.com.alura.escolalura.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<ClosureDTO> getClosure() {
        log.info("AdminService.getClosure start");
        ArrayList<ClosureDTO> closures = new ArrayList<>();

        List<Course> allCourses = courseRepository.findAll();

        allCourses.forEach(course -> {
            List<String> subjectNames = new ArrayList<>();
            List<SubjectStudentInfoDTO> studentData = new ArrayList<>();

            Iterable<Subject> subjects = subjectRepository.findAllById(course.getSubjects());
            subjects.forEach(subject -> subjectNames.add(subject.getName()));

            Iterable<Student> allStudents = studentRepository.findAllById(course.getStudents());

            allStudents.forEach(student -> {
                studentData.add(new SubjectStudentInfoDTO(student.getName(), calculateFinalNotes(student.getSubjects())));
            });

            closures.add(new ClosureDTO(course.getName(), subjectNames, studentData));
        });

        log.info("AdminService.getClosure end - closures: {}", closures);
        return closures;
    }

    private List<Double> calculateFinalNotes(List<SubjectDTO> subjects) {
        List<Double> response = new ArrayList<>();

        subjects.forEach(subjectDTO -> {
            response.add(calculateAverage(subjectDTO.getNotes()));
        });

        return response;
    }

    private Double calculateAverage(List<String> notes) {
        long count = notes.stream().filter(n -> !n.isEmpty()).count();
        double value = notes.stream().filter(n -> !n.isEmpty()).mapToDouble(Double::parseDouble).sum();

        return (value / count);
    }

}
