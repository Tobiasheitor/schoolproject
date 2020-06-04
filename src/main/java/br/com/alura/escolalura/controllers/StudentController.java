package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
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
    private CourseService courseService;

    private final String PAGE_REGISTER = "/aluno/cadastrar";
    private final String PAGE_NOTE_REGISTER = "/nota/cadastrar";

    @GetMapping(PAGE_REGISTER)
    public String register(Model model) {
        Student student = new Student();

        List<Course> courses = courseService.getAll();

        model.addAttribute("student", student);
        model.addAttribute("courses", courses);

        return PAGE_REGISTER;
    }

    @PostMapping("/student/save")
    public String save(@ModelAttribute @Valid Student student, BindingResult bindingResult) {
        log.info("Controller start - save student: {}", student);

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }

        studentService.save(student);

        return "redirect:/";
    }

}
