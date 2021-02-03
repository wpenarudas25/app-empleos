package com.wpenarudas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wpenarudas.model.Usuario;
import com.wpenarudas.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioServiceInterface {
	
	@Autowired
	UsuarioRepo repository;
	
	List<Usuario> lista = null;
 
	@Override
	public Usuario buscarPorId(Long idUsuario) {
		for (Usuario u : lista) {
			if (u.getId() == idUsuario) {
				return u;
			}
		}
		return null;
	}

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		lista.add(usuario);
	}

	@Override
	public List<Usuario> buscarTodas() {
		return (List<Usuario>) repository.findAll();
	}
	
	private boolean checkUsernameAvailable(Usuario usuario) throws Exception {
		Optional<Usuario> userFound = repository.findByUsername(usuario.getUsername());
		if (userFound.isPresent()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}
	
	private boolean checkEmailAvailable(Usuario usuario) throws Exception {
		Optional<Usuario> userFound = repository.findByCorreo(usuario.getCorreo());
		if (userFound.isPresent()) {
			throw new Exception("Email no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(Usuario usuario) throws Exception {
		if ( !usuario.getPassword().equals(usuario.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) throws Exception {
		if (checkUsernameAvailable(usuario) && checkPasswordValid(usuario) && checkEmailAvailable(usuario)) {
			usuario.setPassword(usuario.getPassword());
			usuario= repository.save(usuario);
		} 
		return usuario;
		
	}
}
