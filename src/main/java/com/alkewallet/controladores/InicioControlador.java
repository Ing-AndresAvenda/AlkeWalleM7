package com.alkewallet.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioControlador {

	/**
	 * 
	 * @return Cuendo cargamos la aplicacion nos dirige al index
	 */
	@GetMapping("/")
    public String bienvenido() {
        return "index"; // Nombre de la página de bienvenida (sin la extensión .html)
    }
}
