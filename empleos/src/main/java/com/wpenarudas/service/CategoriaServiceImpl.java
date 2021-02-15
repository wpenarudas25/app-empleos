package com.wpenarudas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpenarudas.model.Categoria;
import com.wpenarudas.repository.CategoriaRepo;

@Service
public class CategoriaServiceImpl implements CategoriaServiceInterface {

	@Autowired
	CategoriaRepo repository;

	List<Categoria> lista = null;

	@Override
	public void guardar(Categoria categoria) {
		lista.add(categoria);

	}

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return (List<Categoria>) repository.findAll();
	}

	@Override
	public Categoria crearCategoria(Categoria categoria) throws Exception {
		categoria = repository.save(categoria);
		return categoria;
	}

	@Override
	public Categoria buscarPorId(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("La Categoria a editar no existe."));
	}

	@Override
	public Categoria actualizarCategoria(Categoria categoria) throws Exception {
		Categoria categoriaEncontrada = buscarPorId(categoria.getId());
		mapCategoria(categoria, categoriaEncontrada);
		return repository.save(categoriaEncontrada);

	}

	protected void mapCategoria(Categoria from, Categoria to) {
		to.setNombre(from.getNombre());
		to.setDescripcion(from.getDescripcion());
	}

	@Override
	public void eliminarCategoria(Long id) throws Exception {
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new Exception("No se encontró la categoría a eliminar." + this.getClass().getName()));
		repository.delete(categoria);
	}

}
