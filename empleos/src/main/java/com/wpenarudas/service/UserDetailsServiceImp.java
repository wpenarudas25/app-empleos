package com.wpenarudas.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wpenarudas.model.Role;
import com.wpenarudas.repository.UsuarioRepo;

@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	UsuarioRepo repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.wpenarudas.model.Usuario appUsuario = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario no existe!"));
		
		Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>(); 
		for (Role role: appUsuario.getRoles()) {
			  GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescripcion());
	            grantList.add(grantedAuthority);
	    } 
		UserDetails usuario = (UserDetails) new User(appUsuario.getUsername(), appUsuario.getPassword(), grantList);
		
		 return usuario;
	}

}
