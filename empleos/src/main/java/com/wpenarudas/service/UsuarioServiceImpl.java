package com.wpenarudas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wpenarudas.dto.ChangePassword;
import com.wpenarudas.model.Usuario;
import com.wpenarudas.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioServiceInterface {

	@Autowired
	UsuarioRepo repository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	List<Usuario> lista = null;

	@Override
	public Usuario buscarPorId(Long idUsuario) throws Exception {
		return repository.findById(idUsuario).orElseThrow(() -> new Exception("El usuario no existe."));
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
		if (!usuario.getPassword().equals(usuario.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) throws Exception {
		if (checkUsernameAvailable(usuario) && checkPasswordValid(usuario) && checkEmailAvailable(usuario)) {
			String encodedPassword = bCryptPasswordEncoder.encode(usuario.getPassword());
			usuario.setPassword(encodedPassword);
			usuario = repository.save(usuario);
		}
		return usuario;

	}
	
	

	@Override
	public Usuario actualizarUsuario(Usuario usuario) throws Exception {
		Usuario usuarioEncontrado = buscarPorId(usuario.getId());
		mapUsuario(usuario, usuarioEncontrado);
		String encodedPassword = bCryptPasswordEncoder.encode(usuarioEncontrado.getPassword());
		usuarioEncontrado.setPassword(encodedPassword);
		String confirmPassword = usuarioEncontrado.getPassword();
		usuarioEncontrado.setConfirmPassword(confirmPassword);
		return repository.save(usuarioEncontrado);
	}

	protected void mapUsuario(Usuario from, Usuario to) {
		to.setUsername(from.getUsername());
		to.setNombre(from.getNombre());
		to.setRoles(from.getRoles());
		to.setCorreo(from.getCorreo());
		to.setEstado(from.getEstado());
		to.setTipo(from.getTipo());
		to.setPassword(from.getPassword());
		to.setConfirmPassword(from.getConfirmPassword());
	}

	@Override
	public void eliminarUsuario(Long id) throws Exception {
		Usuario usuario = repository.findById(id)
				.orElseThrow(() -> new Exception("No se encontró el usuario a eliminar." + this.getClass().getName()));
		repository.delete(usuario);

	}

	@Override
	public Usuario changePassword(ChangePassword form) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EDITOR','ROLE_USER')")
	public Usuario updateUser(Usuario usuario) throws Exception {

		return usuario;

	}

	
}
