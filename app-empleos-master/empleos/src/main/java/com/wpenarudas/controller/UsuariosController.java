package com.wpenarudas.controller;

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
import com.wpenarudas.model.Usuario;
import com.wpenarudas.repository.RolRepo;
import com.wpenarudas.service.UsuarioServiceInterface;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

 	@Autowired
	private UsuarioServiceInterface serviceUsuario;
 	
 	@Autowired
	RolRepo rolRepo;

	@GetMapping("/index")
	public String inicio(Model model) {		
		model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
		model.addAttribute("roles", rolRepo.findAll());	
		return "usuarios/listUsuarios";
	}
	@GetMapping("/create")
	public String crear(Model model) {
		model.addAttribute("userForm", new Usuario());
		model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
		model.addAttribute("roles", rolRepo.findAll());	
		return "usuarios/formUsuarios";
	}
	
	@PostMapping("/create")
	public String crearUsuario(@Validated @ModelAttribute("userForm") Usuario usuario, BindingResult result, ModelMap model)  {
		if(result.hasErrors()) {
			model.addAttribute("userForm", usuario);				
		}else {
			 try {
				serviceUsuario.crearUsuario(usuario);
				model.addAttribute("userForm", new Usuario());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", usuario);
				model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
				model.addAttribute("roles", rolRepo.findAll());
			}
		}
		
		return "usuarios/formUsuarios";
		
	}
}
