package com.wpenarudas.service;

import java.util.List;

import com.wpenarudas.model.Categoria;
public interface CategoriaServiceInterface {	
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	public Categoria crearCategoria(Categoria categoria) throws Exception;
	Categoria buscarPorId(Long id) throws Exception;
	public Categoria actualizarCategoria(Categoria categoria) throws Exception;
}
