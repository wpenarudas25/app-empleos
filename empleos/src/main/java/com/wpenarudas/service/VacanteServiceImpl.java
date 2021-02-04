package com.wpenarudas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpenarudas.model.Vacante;
import com.wpenarudas.repository.VacanteRepo;

@Service
public class VacanteServiceImpl implements VacanteServiceInterface {

	@Autowired
	VacanteRepo repository;
	
	List<Vacante> lista = null;

	public List<Vacante> buscarTodas() {
		return (List<Vacante>) repository.findAll();
		//(List<Vacante>) 
	}

	@Override
	public Vacante buscarPorId(Long idVacante) throws Exception {
		return repository.findById(idVacante).orElseThrow(() -> new Exception("El usuario a editar no existe.")) ;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);

	}

	@Override
	public Vacante crearVacante(Vacante vacante) throws Exception {
		vacante = repository.save(vacante);
		return null;
	}

	@Override
	public Vacante actualizarVacante(Vacante vacante) throws Exception {
		Vacante vacanteEncontrada = buscarPorId(vacante.getId());
		mapVacante(vacante, vacanteEncontrada);
		return repository.save(vacanteEncontrada);
	}

	
	protected void mapVacante(Vacante from, Vacante to) {
		to.setDescripcion(from.getDescripcion());
		to.setDestacado(from.getDestacado());
		to.setFecha(from.getFecha());
		to.setImagen(from.getImagen());
		to.setNombre(from.getNombre());
		to.setSalario(from.getSalario());
	}
	

}












