package com.alkewallet.servicios;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkewallet.modelo.Usuario;
import com.alkewallet.repositorio.UsuarioRepositorio;





@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user =  usuarioRepositorio.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("usuario no encontrado");
		}
		
		Set<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(
				role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	
}