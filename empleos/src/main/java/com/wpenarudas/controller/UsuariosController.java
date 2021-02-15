package com.wpenarudas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.wpenarudas.dto.ChangePassword;
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
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "usuarios/listUsuarios";
	}
	@GetMapping("/create")
	public String crear(Model model) {
		model.addAttribute("userForm", new Usuario());
		model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
		model.addAttribute("roles", rolRepo.findAll());	
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "usuarios/formUsuarios";
	}
	
	@PostMapping("/create")
	public String crearUsuario(@Validated @ModelAttribute("userForm") Usuario usuario, BindingResult result, ModelMap model)  {
		if(result.hasErrors()) {
			model.addAttribute("userForm", usuario);
			model.addAttribute("nombre_usuario", SecurityContextHolder
	                .getContext().getAuthentication().getName());
		}else {
			 try {
				serviceUsuario.crearUsuario(usuario);
				model.addAttribute("userForm", new Usuario());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", usuario);
				model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
				model.addAttribute("roles", rolRepo.findAll());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			}
		}
		
		return "usuarios/formUsuarios";
		
	}
	
	
	@GetMapping("/registro")
	public String registarUsuario(Model model) {
		model.addAttribute("userForm", new Usuario());
		model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
		model.addAttribute("roles", rolRepo.findAll());	
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "usuarios/registro";
	}
	@PostMapping("/registro")
	public String registarUsuario(@Validated @ModelAttribute("userForm") Usuario usuario, BindingResult result, ModelMap model)  {
		model.addAttribute("userForm", usuario);
		model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
		model.addAttribute("roles", rolRepo.findAll());	
		if(result.hasErrors()) {
			return "usuarios/registro";
		}else {
			 try {
				 usuario.setTipo("USER");
				serviceUsuario.crearUsuario(usuario);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				}
		}
		return "redirect:/login";
	}
	
	
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		Usuario usuarioEditar = serviceUsuario.buscarPorId(id);		
		model.addAttribute("usuarios", serviceUsuario.buscarTodas());
		model.addAttribute("roles",rolRepo.findAll());
		model.addAttribute("userForm", usuarioEditar);	
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		//model.addAttribute("editMode",true);//Mira siguiente seccion para mas informacion
		
		model.addAttribute("passwordForm",new ChangePassword(id));
		
		return "usuarios/editarUsuario";
	}
	
	@PostMapping("/editUser")
	public String editarUsuario(@Validated @ModelAttribute("userForm") Usuario usuario, BindingResult result, ModelMap model)  {
		if(result.hasErrors()) {
			model.addAttribute("userForm", usuario);		
			model.addAttribute("passwordForm",new ChangePassword(usuario.getId()));model.addAttribute("nombre_usuario", SecurityContextHolder
	                .getContext().getAuthentication().getName());
		}else {
			 try {
				serviceUsuario.actualizarUsuario(usuario);
				model.addAttribute("userForm", new Usuario());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", usuario);
				model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
				model.addAttribute("roles", rolRepo.findAll());
				model.addAttribute("passwordForm",new ChangePassword(usuario.getId()));model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			}
		}
		
		return "redirect:/usuarios/index";
				
	}
	
	@GetMapping("/editUserPassword/{id}")
	public String getEditUserPasswordForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		Usuario usuarioEditar = serviceUsuario.buscarPorId(id);		
		model.addAttribute("usuarios", serviceUsuario.buscarTodas());
		model.addAttribute("roles",rolRepo.findAll());
		model.addAttribute("userForm", usuarioEditar);		
		//model.addAttribute("editMode",true);//Mira siguiente seccion para mas informacion
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		
		model.addAttribute("passwordForm",new ChangePassword(id));
		
		return "usuarios/changePassword";
	}
	
	@PostMapping("/editUserPassword")
	public String editarUsuarioPassword(@Validated @ModelAttribute("userForm") Usuario usuario, BindingResult result, ModelMap model)  {
		if(result.hasErrors()) {
			model.addAttribute("userForm", usuario);		
			model.addAttribute("passwordForm",new ChangePassword(usuario.getId()));
			model.addAttribute("nombre_usuario", SecurityContextHolder
	                .getContext().getAuthentication().getName());
		}else {
			 try {
				serviceUsuario.actualizarUsuario(usuario);
				model.addAttribute("userForm", new Usuario());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("userForm", usuario);
				model.addAttribute("usuarios", serviceUsuario.buscarTodas());		
				model.addAttribute("roles", rolRepo.findAll());
				model.addAttribute("passwordForm",new ChangePassword(usuario.getId()));
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			}
		}
		
		return "redirect:/usuarios/index";
				
	}	
	
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(Model model,  @PathVariable(name="id") Long id ) {
		try {
			serviceUsuario.eliminarUsuario(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return "redirect:/usuarios/index";
	}
	
	
	@SuppressWarnings("unused")
	private boolean isLoggedUserADMIN() {
		//Obtener el usuario logeado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;
		Object roles = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;

			roles = loggedUser.getAuthorities().stream()
					.filter(x -> "ROLE_ADMIN".equals(x.getAuthority())).findFirst()
					.orElse(null); 
		}
		return roles != null ? true : false;
	}
}
