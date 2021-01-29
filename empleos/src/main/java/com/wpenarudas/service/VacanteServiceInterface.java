package com.wpenarudas.service;

import java.util.List;

import com.wpenarudas.model.Vacante;

public interface VacanteServiceInterface {
	Vacante buscarPorId(Integer idVacante);
	void guardar(Vacante vacante);
	List<Vacante> buscarTodas();
}
