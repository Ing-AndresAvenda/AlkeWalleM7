package com.alkewallet.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkewallet.repositorio.TransaccionRepositorio;

@Controller
@RequestMapping("/movimientos")
public class TransaccionControlador {
	
	@Autowired
	private TransaccionRepositorio transaccionRepositorio;
	/**
	 * 
	 * @param model asignamos a mocimientos los valores traidos por transaccionRepositorio
	 * @return ruta de nuestro html que queremos mostrar
	 */
	@GetMapping
	public String listarTransacciones(Model model) {  // USAMOS MODEL PARA PASAR LOS DATOS A LA VISTA
        model.addAttribute("movimientos", transaccionRepositorio.findAll());
        return "movimientos/listaMovimientos";
    }
	

}
