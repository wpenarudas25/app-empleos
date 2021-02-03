package com.wpenarudas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wpenarudas.model.Categoria;
import com.wpenarudas.service.CategoriaServiceInterface;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	private CategoriaServiceInterface serviceCategoria;

	@GetMapping("/index")
	public String inicio(Model model) {				
		List<Categoria> lista = serviceCategoria.buscarTodas();
		model.addAttribute("categorias", lista);		
		return "categorias/listCategorias";
	}


	// @GetMappig("/create")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String crear() {
		return "categorias/formCategorias";
	}

	// @GetMappig("/save")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
		System.out.println("Categoria: "+ nombre);
		System.out.println("Descripcion: "+ descripcion);
		return "categorias/listCategorias";
	}
}
