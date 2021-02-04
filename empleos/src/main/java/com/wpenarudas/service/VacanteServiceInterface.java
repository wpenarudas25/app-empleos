package com.wpenarudas.service;

import java.util.List;

import com.wpenarudas.model.Vacante;

public interface VacanteServiceInterface {
	Vacante buscarPorId(Long idVacante) throws Exception;
	void guardar(Vacante vacante);
	List<Vacante> buscarTodas();
	public Vacante crearVacante(Vacante vacante) throws Exception;
	public Vacante actualizarVacante(Vacante vacante) throws Exception;
}
