package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.dto.ContactDTO;
import br.com.alura.escolalura.entity.Course;
import br.com.alura.escolalura.entity.Student;
import br.com.alura.escolalura.service.CourseService;
import br.com.alura.escolalura.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private final String PAGE_REGISTER = "/aluno/cadastrar";

    @GetMapping(PAGE_REGISTER)
    public String register(Model model) {
        Student student = new Student();
        student.setContact(new ContactDTO());

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

        student = studentService.save(student);

        log.info("Controller end - saved student: {}", student);
        return "redirect:/";
    }

    @GetMapping("/aluno/listar")
    public String listar(Model model) {
		/*
		List<Aluno> alunos = repository.obterTodosAlunos();
		model.addAttribute("alunos", alunos);
		*/
        //model.addAttribute("alunos", studentService.getAll());

        return "aluno/listar";
    }

    @GetMapping("/aluno/visualizar/{id}")
    public String visualizar(@PathVariable String id, Model model) {
		/*
		Aluno aluno = repository.obterAlunoPor(id);

		model.addAttribute("aluno", aluno);
		*/
        return "aluno/visualizar";
    }

    @GetMapping("/aluno/pesquisarnome")
    public String pesquisarNome() {
        return "aluno/pesquisarnome";
    }

    @GetMapping("/aluno/pesquisar")
    public String pesquisar(@RequestParam("nome") String nome, Model model) {
		/*
		List<Aluno> alunos = repository.pesquisarPor(nome);

		model.addAttribute("alunos", alunos);

		*/
        return "aluno/pesquisarnome";
    }
}
