package com.alkewallet.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.alkewallet.modelo.Usuario;
import com.alkewallet.repositorio.UsuarioRepositorio;
/**
 * Controlador encargado de realizar las validaciones de los usuarios 
 */
@Controller
public class ValidaUserControlador {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
	public String formularioLogin() {
		return "clientes/login";
	}
	
	@GetMapping("/registro/nuevo")
	public String formularioRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "clientes/registro";
	}
	
	@PostMapping("/registro")
	public String registroUsuario(@ModelAttribute("usuario") Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuarioRepositorio.save(usuario);
		return "redirect:/login";
	}

}
