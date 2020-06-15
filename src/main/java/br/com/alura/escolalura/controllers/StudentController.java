package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.dto.ModelStudent;
import br.com.alura.escolalura.dto.TesteDTO;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.repository.StudentRepository;
import br.com.alura.escolalura.service.CourseService;
import br.com.alura.escolalura.service.StudentService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String saveNote(@ModelAttribute TesteDTO teste) {
        log.info("Controller saveNote start - save teste: {}", teste);

        return "redirect:/";
    }

}
