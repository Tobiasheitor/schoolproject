package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.dto.ModelCourse;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.service.CourseService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class CourseController {

    private static final String PAGE_REGISTER = "/course/register";
    private static final String GET_COURSE_STUDENTS = "course/{courseId}/students";
    private static final String SELECT_STUDENTS_FRAGMENT = "layout/fragments :: select_student";

    @Autowired
    private CourseService courseService;

    @GetMapping("/course/register")
    public String newCourse(Model model) {
        ModelCourse course = new ModelCourse();

        model.addAttribute("course", course);
        model.addAttribute("students", course);

        return PAGE_REGISTER;
    }

    @PostMapping("/course/save")
    public String save(@ModelAttribute @Valid ModelCourse course, BindingResult bindingResult) {
        log.info("Controller start - save course: {}", course);
        Course newCourse;

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }

        try {
            newCourse = courseService.save(course);
        } catch (org.springframework.dao.DuplicateKeyException ex) {
            bindingResult.addError(new FieldError("course", "name", "Este curso já esta cadastrado"));
            return PAGE_REGISTER;
        }

        log.info("Controller end - saved course: {}", newCourse);
        return "redirect:/";
    }

    @GetMapping(GET_COURSE_STUDENTS)
    public String getCourseStudents(Model model, @PathVariable("courseId") String courseId) {

        model.addAttribute("students", courseService.getCourseStudents(courseId));

        return SELECT_STUDENTS_FRAGMENT;
    }

}
