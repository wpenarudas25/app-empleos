package com.wpenarudas.service;

import java.util.List;

import com.wpenarudas.model.Usuario;

public interface UsuarioServiceInterface {
	void guardar(Usuario usuario);
	List<Usuario> buscarTodas();
	Usuario buscarPorId(Long idUsuario);
}
