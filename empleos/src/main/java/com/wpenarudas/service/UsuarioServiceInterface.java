package com.wpenarudas.service;

import com.wpenarudas.model.Usuario;

public interface UsuarioServiceInterface {
	void guardar(Usuario usuario);
	Iterable<Usuario> buscarTodas();
	Usuario buscarPorId(Long idUsuario) throws Exception;
	public Usuario crearUsuario(Usuario usuario) throws Exception;
	public Usuario actualizarUsuario(Usuario usuario) throws Exception;
}
