package com.wpenarudas.service;

import java.util.List;

import com.wpenarudas.model.Categoria;

public interface CategoriaServiceInterface {

	Categoria buscarPorId(Integer idCategoria);
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	public Categoria crearCategoria(Categoria categoria) throws Exception;
}
