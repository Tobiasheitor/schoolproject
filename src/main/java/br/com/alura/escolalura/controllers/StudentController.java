package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.dto.CreateUserDTO;
import br.com.alura.escolalura.dto.ModelStudent;
import br.com.alura.escolalura.dto.SubjectStudentNotesDTO;
import br.com.alura.escolalura.dto.TesteDTO;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.repository.StudentRepository;
import br.com.alura.escolalura.service.CourseService;
import br.com.alura.escolalura.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;

    private static final String PAGE_REGISTER = "/aluno/cadastrar";
    private static final String PAGE_NOTE_REGISTER = "/nota/cadastrar";
    private static final String PAGE_NOTE_SAVE = "/note/save";
    private static final String STUDENT_SUBJECTS = "/student/{studentId}/subjects";
    private static final String INPUT_NOTES_FRAGMENT = "layout/fragments :: input_notes";

    @GetMapping(PAGE_REGISTER)
    public String register(Model model) {
        ModelStudent modelStudent = new ModelStudent();

        List<Course> courses = courseService.getAll();

        model.addAttribute("student", modelStudent);
        model.addAttribute("courses", courses);

        return PAGE_REGISTER;
    }

    @PostMapping("/student/save")
    public String save(@ModelAttribute @Valid ModelStudent modelStudent, BindingResult bindingResult) {
        log.info("Controller start - save student: {}", modelStudent);

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }

        Student studentSaved = studentService.save(modelStudent);

        log.info("StudentController.save end - student {}", studentSaved);
        return "redirect:/";
    }

    @GetMapping(PAGE_NOTE_REGISTER)
    public String registerNotes(Model model) {
        log.info("Controller.registerNotes start");

        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("teste", new TesteDTO());

        return PAGE_NOTE_REGISTER;
    }

    @PostMapping(PAGE_NOTE_SAVE)
    public ResponseEntity<String> saveNote(@RequestBody CreateUserDTO createUserDTO) {
        log.info("{}", createUserDTO);

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @GetMapping(STUDENT_SUBJECTS)
    public String getSubjects(Model model, @PathVariable("studentId") String studentId) {
        log.info("StudentController.getSubjects - studentId: {}", studentId);

        List<SubjectStudentNotesDTO> studentSubjects = studentService.getStudentSubjects(studentId);

        studentSubjects.get(0).setNotes(Arrays.asList("2.0", "3.5", ""));
        studentSubjects.get(1).setNotes(Arrays.asList("2.0", "3.5", ""));
        model.addAttribute("notes", studentSubjects);

        return INPUT_NOTES_FRAGMENT;
    }

}
