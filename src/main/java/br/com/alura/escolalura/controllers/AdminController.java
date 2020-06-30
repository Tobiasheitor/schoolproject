package br.com.alura.escolalura.controllers;

import br.com.alura.escolalura.service.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class AdminController {
	private static final String PAGE_CLOSURE = "/closure";

	@Autowired
	private AdminServiceImpl adminService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping(PAGE_CLOSURE)
	public String closure(Model model) {

		model.addAttribute("closure", adminService.getClosure());

		return PAGE_CLOSURE;
	}

}
