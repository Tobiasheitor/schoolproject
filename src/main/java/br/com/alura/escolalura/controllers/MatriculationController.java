package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MatriculationController {

    private static final String PAGE_MATRICULATION = "/matriculation/register";

    @Autowired
    private CourseService courseService;

    @GetMapping(PAGE_MATRICULATION)
    public String newMatriculation(Model model) {

        List<Course> courses = courseService.getAll();
        model.addAttribute("courses", courses);

        return PAGE_MATRICULATION;
    }

}
