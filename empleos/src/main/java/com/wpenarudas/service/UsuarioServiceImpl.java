package com.wpenarudas.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.wpenarudas.model.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioServiceInterface {
	
	List<Usuario> lista = null;
	
	public UsuarioServiceImpl() {
		lista = new LinkedList<Usuario>();
		
		try {
			//Usuario 1}
			Usuario usuarioadmin = new Usuario();
			usuarioadmin.setNombre("Wilmar Peña Rudas");
			usuarioadmin.setCorreo("wpenarudas@gmail.com");
			usuarioadmin.setUsername("wpenarudas");
			usuarioadmin.setPassword("justdoit");
			usuarioadmin.setEstado("Activo");
			usuarioadmin.setTipo("admin");
			
			//Usuario 1}
			Usuario usuarioEdit = new Usuario();
			usuarioEdit.setNombre("GAriammys Calderón Quintana");
			usuarioEdit.setCorreo("gariammys8572@gmail.com");
			usuarioEdit.setUsername("gary");
			usuarioEdit.setPassword("trotamundos");
			usuarioEdit.setEstado("Activo");
			usuarioEdit.setTipo("editor");
			
			//Usuario 1}
			Usuario usuario = new Usuario();
			usuario.setNombre("Gaby Calderón Quitana");
			usuario.setCorreo("gabycq@gmail.com");
			usuario.setUsername("gabycq");
			usuario.setPassword("gabycq");
			usuario.setEstado("Activo");
			usuario.setTipo("usuario");
			
			lista.add(usuarioadmin);
			lista.add(usuarioEdit);
			lista.add(usuario);			
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

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
		return lista;
	}

}
