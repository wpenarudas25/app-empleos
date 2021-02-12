package com.wpenarudas.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wpenarudas.model.Vacante;
import com.wpenarudas.service.VacanteServiceInterface;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	@Autowired
	private VacanteServiceInterface serviceVacantes;
	
	@GetMapping("/index")
	public String inicio(Model model) {				
		model.addAttribute("vacantes", serviceVacantes.buscarTodas());
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "vacantes/listVacantes";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		model.addAttribute("vacanteForm", new Vacante());
		model.addAttribute("vacantes", serviceVacantes.buscarTodas());	
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "vacantes/formVacantes";
	}
	
	@PostMapping("/create")
	public String crearVacante(@Validated @ModelAttribute("vacanteForm") Vacante  vacante, BindingResult result, ModelMap model)  {
		if(result.hasErrors()) {
			model.addAttribute("vacanteForm", vacante);		
			model.addAttribute("nombre_usuario", SecurityContextHolder
	                .getContext().getAuthentication().getName());
		}else {
			 try {
				serviceVacantes.crearVacante(vacante);
				model.addAttribute("vacanteForm", new Vacante());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("vacanteForm", vacante);
				model.addAttribute("vacantes", serviceVacantes.buscarTodas());	
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			}
		}
		
		return "vacantes/formVacantes";
		
	}

	@PostMapping("/save")
	public String guardar(Vacante vacante, Model model) {
		serviceVacantes.guardar(vacante);
		System.out.println("Vacante: " + vacante);
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		return "vacantes/listVacantes";
	}

	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") Long idVacante, Model model) {
		System.out.println("Borrando Vacante con id: " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") Long idVacante, Model model) throws Exception {
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());

		// Buscar los detalles en la BD...

		return "/detalle";
	}

	@GetMapping("/aplicar/{id}")
	public String aplicarVacante(@PathVariable("id") Long idVacante, Model model) throws Exception {
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		System.out.println("Vacante: " + vacante);
		model.addAttribute("vacante", vacante);
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());

		// Buscar los detalles en la BD...

		return "/vacantes/formSolicitud";
	}
	
	@GetMapping("/editVacante/{id}")
	public String getEditVacanteForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		Vacante vacanteEditar = serviceVacantes.buscarPorId(id);		
		model.addAttribute("vacantes", serviceVacantes.buscarTodas());
		model.addAttribute("vacanteForm", vacanteEditar);	
		model.addAttribute("nombre_usuario", SecurityContextHolder
                .getContext().getAuthentication().getName());
		
		return "vacantes/editVacante";
	}

	
	@PostMapping("/editVacante")
	public String editarVacante(@Validated @ModelAttribute("vacanteForm") Vacante vacante, BindingResult result, ModelMap model)  {
		if(result.hasErrors()) {
			model.addAttribute("vacanteForm", vacante);		
			model.addAttribute("nombre_usuario", SecurityContextHolder
	                .getContext().getAuthentication().getName());
		}else {
			 try {
				serviceVacantes.actualizarVacante(vacante);
				model.addAttribute("vacanteForm", new Vacante());
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", e.getMessage());
				model.addAttribute("vacanteForm", vacante);
				model.addAttribute("vacantes", serviceVacantes.buscarTodas());	
				model.addAttribute("nombre_usuario", SecurityContextHolder
		                .getContext().getAuthentication().getName());
			}
		}		
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/eliminarVacante/{id}")
	public String eliminarVacante(Model model,  @PathVariable(name="id") Long id ) {
		try {
			serviceVacantes.eliminarVacante(id);
		} catch (Exception e) {
			model.addAttribute("listErrorMessage", e.getMessage());
		}
		return "redirect:/vacantes/index";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateformat, false));
	}
}
