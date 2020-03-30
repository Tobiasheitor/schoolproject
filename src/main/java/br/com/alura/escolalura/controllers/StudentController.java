package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @GetMapping("/aluno/cadastrar")
    public String register(Model model) {
        model.addAttribute("student", new Student());
        return "/aluno/cadastrar";
    }

    @PostMapping("/aluno/salvar")
    public String save(@ModelAttribute Student student) {

        //student = studentService.save(student);

        // System.out.println("StudentController.save - Student " + student.toString());

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
