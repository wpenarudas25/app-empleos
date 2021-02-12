package com.wpenarudas.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wpenarudas.model.Vacante;
import com.wpenarudas.service.VacanteServiceInterface;

@Controller
public class HomeController {

	@Autowired
	private VacanteServiceInterface serviceVacantes;

	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);

		return "tabla";
	}

	@GetMapping("/")
	public String inicio(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "/inicio";
	}

	@GetMapping("/login")
	public String login(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "login";
	}

	@GetMapping("/registro")
	public String registro(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "registro";
	}

}
