package com.wpenarudas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "categorias/listCategorias";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		model.addAttribute("categoriaForm", new Categoria());
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "categorias/formCategorias";
	}

	@PostMapping("/create")
	public String crearUsuario(@Validated @ModelAttribute("categoriaForm") Categoria categoria, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("categoriaForm", categoria);
			model.addAttribute("nombre_usuario", SecurityContextHolder
	                .getContext().getAuthentication().getName());
		} else {
			try {
				serviceCategoria.crearCategoria(categoria);
				model.addAttribute("categoriaForm", new Categoria());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("categoriaForm", categoria);
				model.addAttribute("categorias", serviceCategoria.buscarTodas());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			}
		}

		return "categorias/formCategorias";

	}

	@GetMapping("/editCategoria/{id}")
	public String getEditUserForm(Model model, @PathVariable(name = "id") Long id) throws Exception {
		Categoria categoriaEditar = serviceCategoria.buscarPorId(id);
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
		model.addAttribute("categoriaForm", categoriaEditar);
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());

		// model.addAttribute("editMode",true);//Mira siguiente seccion para mas
		// informacion

		return "categorias/editarcategoria";
	}

	@PostMapping("/editCategoria")
	public String editarCategoria(@Validated @ModelAttribute("categoriaForm") Categoria categoria, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("categoriaForm", categoria);
			model.addAttribute("nombre_usuario", SecurityContextHolder
	                .getContext().getAuthentication().getName());
		} else {
			try {
				serviceCategoria.actualizarCategoria(categoria);
				model.addAttribute("categoriaForm", new Categoria());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("categoriaForm", categoria);
				model.addAttribute("categorias", serviceCategoria.buscarTodas());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			}
		}

		return "redirect:/categorias/index";

	}
	
	@GetMapping("/eliminarCategoria/{id}")
	public String eliminarCategoria(Model model,  @PathVariable(name="id") Long id ) {
		try {
			serviceCategoria.eliminarCategoria(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return "redirect:/categorias/index";
		
	}

}
