package com.wpenarudas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wpenarudas.model.Categoria;
import com.wpenarudas.service.CategoriaServiceInterface;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriasController {

	@Autowired
	private CategoriaServiceInterface serviceCategoria;

	@GetMapping("/index")
	public String inicio(Model model) {
		List<Categoria> lista = serviceCategoria.buscarTodas();
		model.addAttribute("categorias", lista);
		return "categorias/listCategorias";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		model.addAttribute("categoriaForm", new Categoria());
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
		return "categorias/formCategorias";
	}

	@PostMapping("/create")
	public String crearUsuario(@Validated @ModelAttribute("categoriaForm") Categoria categoria, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("categoriaForm", categoria);
		} else {
			try {
				serviceCategoria.crearCategoria(categoria);
				model.addAttribute("categoriaForm", new Categoria ());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("categoriaForm", categoria);
				model.addAttribute("categorias", serviceCategoria.buscarTodas());
			}
		}

		return "categorias/formCategorias";

	}
}
