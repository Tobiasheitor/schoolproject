package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.LinkedHashMap;

@Slf4j
@Controller
public class CourseController {

    private static final String PAGE_REGISTER = "/course/register";

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/register")
    public String newCourse(Model model) {
        LinkedHashMap<String, String> grade = new LinkedHashMap<>();
        grade.put("segunda-feira", "-");
        grade.put("terça-feira", "-");
        grade.put("quarta-feira", "-");
        grade.put("quinta-feira", "-");
        grade.put("sexta-feira", "-");

        model.addAttribute("course", new Course());
        model.addAttribute("timetable", grade);

        return PAGE_REGISTER;
    }

    @PostMapping("/course/save")
    public String save(@ModelAttribute @Valid Course course, BindingResult bindingResult) {
        log.info("Controller start - save course: {}", course);

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }

        try {
            course = courseService.save(course);
        } catch (org.springframework.dao.DuplicateKeyException ex) {
            bindingResult.addError(new FieldError("course", "name", "Este curso já esta cadastrado"));
            return PAGE_REGISTER;
        }

        log.info("Controller end - saved course: {}", course);
        return "redirect:/";
    }

}
