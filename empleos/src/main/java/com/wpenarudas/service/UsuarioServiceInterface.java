package com.wpenarudas.service;

import java.util.List;

import com.wpenarudas.model.Usuario;

public interface UsuarioServiceInterface {
	Usuario  buscarPorId(Integer idUsuario);
	void guardar(Usuario usuario);
	List<Usuario> buscarTodas();
}
