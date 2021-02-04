package com.wpenarudas.service;


import com.wpenarudas.model.Categoria;
public interface CategoriaServiceInterface {	
	void guardar(Categoria categoria);
	Iterable<Categoria> buscarTodas();
	public Categoria crearCategoria(Categoria categoria) throws Exception;
	Categoria buscarPorId(Long id) throws Exception;
	public Categoria actualizarCategoria(Categoria categoria) throws Exception;
	public void eliminarCategoria(Long id) throws Exception;
}
