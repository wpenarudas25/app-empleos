package com.wpenarudas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wpenarudas.model.Usuario;
import com.wpenarudas.service.UsuarioServiceInterface;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	private UsuarioServiceInterface serviceUsuario;

	@GetMapping("/index")
	public String inicio(Model model) {				
		List<Usuario> lista = serviceUsuario.buscarTodas();
		model.addAttribute("usuarios", lista);		
		return "usuarios/listUsuarios";
	}
	@GetMapping("/create")
	public String crear() {
		return "usuarios/formUsuarios";
	}
}
