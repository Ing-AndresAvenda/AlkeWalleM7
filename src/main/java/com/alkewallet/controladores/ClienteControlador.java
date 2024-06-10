package com.alkewallet.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alkewallet.modelo.Cliente;
import com.alkewallet.servicios.ClienteService;

import jakarta.validation.Valid;




/**
 * @author Andres A
 * Controlador encargado de gestionar funcionalidades 
 * de un cliente, creacion, lista de clientes, transferencia entre clientes
 * depositos, retiros
 */
@Controller
@RequestMapping("/vistaClientes")
public class ClienteControlador {

	@Autowired
	private ClienteService clienteService;

	
	/**
	 * 
	 * @param model
	 * @return Regresa la lista de los clientes Creados
	 */
	@GetMapping("/nuevoCrearCliente")
	public String formularioCrearCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "clientes/crearClientes";
	}

	@PostMapping("guardar")
	public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return "clientes/crearClientes";
		}
		
		clienteService.guardar(cliente);
		return "redirect:/vistaClientes";

	}

	@GetMapping
	public String listaClientes(Model model) {
		model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
		return "clientes/listarClientes";
	}

	@GetMapping("/{id}")
	public String verInfoCliente(@PathVariable Integer id, Model model) {
		model.addAttribute("cliente", clienteService.obtenerClientePorId(id).orElse(null));
		return "clientes/verClientes";

	}
	@GetMapping("/transferirFondos")
    public String mostrarFormularioTransferencia(Model model) {
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "clientes/hacerTransfer";
    }

	@PostMapping("transferirFondos")
	public String transferirFondos(@Valid @RequestParam Integer idClienteOrigen, @RequestParam Integer idClienteDestino,
			@RequestParam double monto, BindingResult result) {
		
		if (clienteService.transferirFondosEntreCuentas(idClienteOrigen, idClienteDestino, monto)) {
			return "redirect:/vistaClientes";
		} else {
			return "redirect:/clientes/transferir?error";
		}

	}
	
	@GetMapping("/depositarCuenta")
	 public String mostrarCuentaDeposito(Model model) {
        model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
        return "depositar/depositarCuenta"; // TURA DE HTML
    }
	@PostMapping("depositarCuenta")
	 public String depositarCuenta(@RequestParam Integer id_Cliente, @RequestParam double monto) {
       clienteService.depositarFondos(id_Cliente, monto);
       return "redirect:/vistaClientes";
      // return "depositar/depositarCuenta"; // RUTA DE HTML
   }
	@GetMapping("/retirarDeCuenta")
	 public String mostrarCuentaRetiro(Model model) {
       model.addAttribute("clientes", clienteService.obtenerTodosLosClientes());
       return "retirar/retirarCuenta"; // TURA DE HTML
   }
	@PostMapping("retirarDeCuenta")
	 public String retirarDeCuenta(@RequestParam Integer id_Cliente, @RequestParam double monto) {
      clienteService.retirarFondos(id_Cliente, monto);
      return "redirect:/vistaClientes";
     // return "depositar/depositarCuenta"; // RUTA DE HTML
  }

}
