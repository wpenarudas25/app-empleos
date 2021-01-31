package com.wpenarudas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wpenarudas.model.Usuario;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario, Long> {
	
	//public Iterable<Usuario> findAllByStatus(String estado);
	
}
