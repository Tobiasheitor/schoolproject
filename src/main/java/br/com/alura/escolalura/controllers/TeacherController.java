package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.entity.Teacher;
import br.com.alura.escolalura.service.TeacherService;
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

@Slf4j
@Controller
public class TeacherController {

    private final String PAGE_REGISTER = "/teacher/register";

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher/register")
    public String register(Model model) {
        model.addAttribute("teacher", new Teacher());

        return PAGE_REGISTER;
    }

    @PostMapping("/teacher/register")
    public String save(@ModelAttribute @Valid Teacher teacher, BindingResult bindingResult) {
        log.info("Controller start - save teacher: {}", teacher);

        if (bindingResult.hasErrors()) {
            return PAGE_REGISTER;
        }

        try {
            teacherService.save(teacher);
        } catch (org.springframework.dao.DuplicateKeyException ex) {
            bindingResult.addError(new FieldError("teacher", "name", "Este professor já esta cadastrado"));
            return PAGE_REGISTER;
        }

        log.info("Controller end - saved teacher: {}", teacher);
        return "redirect:/";
    }

}
