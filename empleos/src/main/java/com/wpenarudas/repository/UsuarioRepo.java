package com.wpenarudas.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.wpenarudas.model.Usuario;

@Repository
public interface UsuarioRepo extends CrudRepository<Usuario, Long> {

	public Optional<Usuario> findByUsername(String username);
	public Optional<Usuario> findByCorreo(String correo);
	
	//public Iterable<Usuario> findAllByStatus(String estado);
	
}
