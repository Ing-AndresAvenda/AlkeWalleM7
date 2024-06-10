package com.alkewallet.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkewallet.modelo.Cliente;
import com.alkewallet.modelo.Cuenta;
import com.alkewallet.modelo.Transaccion;
import com.alkewallet.repositorio.ClienteRepositorio;
import com.alkewallet.repositorio.TransaccionRepositorio;
/**
 * Servicio de CLiente donde se encuentras los metodos como guardar, obtener los clientes para mostrar en la lista
 * depositar fondos, retirar o transferir entre las cuentas que existan
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Autowired
	private TransaccionRepositorio transaccionRepositorio;

	@Autowired
	private CuentaService cuentaService;

	public List<Cliente> obtenerTodosLosClientes() {
		return clienteRepositorio.findAll();
	}

	public Optional<Cliente> obtenerClientePorId(Integer id) {
		return clienteRepositorio.findById(id);
	}

	public Cliente guardar(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}

	public void eliminar(Integer id) {
		clienteRepositorio.deleteById(id);
	}

	public void depositarFondos(Integer id_Cliente, double monto) {

		Cliente cliente = clienteRepositorio.findById(id_Cliente)
				.orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
		Cuenta cuenta = cliente.getCuenta();
		cuentaService.depositar(cuenta, monto);
		clienteRepositorio.save(cliente);

		Transaccion transaccion = new Transaccion(LocalDateTime.now(), "(+)DEPOSITO", monto,
				cliente.getCuenta().getId_Cuenta(), null);
		transaccionRepositorio.save(transaccion);
	}

	public void retirarFondos(Integer id_Cliente, double monto) {
		Cliente cliente = clienteRepositorio.findById(id_Cliente)
				.orElseThrow(() -> new IllegalArgumentException("****Cliente no encontrado****"));
		Cuenta cuenta = cliente.getCuenta();
		cuentaService.retirar(cuenta, monto);
		clienteRepositorio.save(cliente);

		Transaccion transaccion = new Transaccion(LocalDateTime.now(), "(-)RETIRO", monto,
				cliente.getCuenta().getId_Cuenta(), null);
		transaccionRepositorio.save(transaccion);
	}

	public boolean transferirFondosEntreCuentas(Integer idClienteOrigen, Integer idClienteDestino, double monto) {
		Cliente clienteOrigen = clienteRepositorio.findById(idClienteOrigen)
				.orElseThrow(() -> new IllegalArgumentException("****Cliente no encontrado****"));
		Cliente clienteDestino = clienteRepositorio.findById(idClienteDestino)
				.orElseThrow(() -> new IllegalArgumentException("****Cliente no encontrado***"));

		if (clienteOrigen.getCuenta().getSaldoCuenta() >= monto) {
			cuentaService.retirar(clienteOrigen.getCuenta(), monto);
			cuentaService.depositar(clienteDestino.getCuenta(), monto);
			
			//clienteOrigen.getCuenta().retirar(monto);
			//clienteDestino.getCuenta().depositar(monto);

			clienteRepositorio.save(clienteOrigen);
			clienteRepositorio.save(clienteDestino);

			Transaccion transaccion = new Transaccion(LocalDateTime.now(), "**TRANSFERENCIA**", monto,
					clienteOrigen.getCuenta().getId_Cuenta(), clienteDestino.getCuenta().getId_Cuenta());
			transaccionRepositorio.save(transaccion);

			return true;
		}

		return false;
	}

}
