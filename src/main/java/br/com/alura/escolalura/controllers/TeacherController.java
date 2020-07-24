package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.dto.ModelTeacher;
import br.com.alura.escolalura.repository.CourseRepository;
import br.com.alura.escolalura.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class TeacherController {
    private static final String PAGE_REGISTER = "teacher/register";
    private static final String HOME_PAGE = "redirect:/";

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherService teacherService;

    @GetMapping(PAGE_REGISTER)
    public String newTeacher(Model model) {
        ModelTeacher teacher = new ModelTeacher();

        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("teacher", teacher);

        return PAGE_REGISTER;
    }

    @PostMapping(PAGE_REGISTER)
    public String save(@ModelAttribute ModelTeacher teacher) {
        log.info("Controller start - save teacher: {}", teacher);

        teacherService.saveTeacher(teacher);

        log.info("Controller end - saved teacher: {}", teacher);
        return HOME_PAGE;
    }
}
