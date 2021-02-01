package com.wpenarudas.service;

import java.util.LinkedList;
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
	
	public CategoriaServiceImpl () {
		lista = new LinkedList<Categoria>();
		
		try {
			//Categoría numero 1
			Categoria categoria1 = new Categoria();
			categoria1.setId(1);
			categoria1.setNombre("Ventas");
			categoria1.setDescripcion("Todo lo relacionado con el area de ventas y mercadeo");
			
			//Categoría numero 1
			Categoria categoria2 = new Categoria();
			categoria2.setId(2);
			categoria2.setNombre("Desarollo");
			categoria2.setDescripcion("Diseño de aplicaciones y webApps a la medida.");
			
			//Categoría numero 1
			Categoria categoria3 = new Categoria();
			categoria3.setId(3);
			categoria3.setNombre("Recursos Humanos");
			categoria3.setDescripcion("Trabajos relacionados con el area de RH.");
			
			//Categoría numero 1
			Categoria categoria4 = new Categoria();
			categoria4.setId(4);
			categoria4.setNombre("Arquitectura");
			categoria4.setDescripcion("	Diseño de planos en general y trabajos relacionados.");
			
			lista.add(categoria1);
			lista.add(categoria2);
			lista.add(categoria3);
			lista.add(categoria4);
			
		} catch (Exception e) {
			System.out.println("Error: " +e.getMessage());
		}
	}
	
	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for (Categoria c : lista) {
			if (c.getId() == idCategoria) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void guardar(Categoria categoria) {
		lista.add(categoria);
		
	}

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return (List<Categoria>) repository.findAll();
	}

}
