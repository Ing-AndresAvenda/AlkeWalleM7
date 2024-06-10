package com.alkewallet.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alkewallet.modelo.Cuenta;
/**
 * Servico Cuenta donde estan las operaciones de depositar y retirar
 */
@Service
public class CuentaService {

	// METODO PARA RETIRAR DINERO EN UNA CUENTA
	@Transactional
	public void retirar(Cuenta cuenta, double monto) {

		if (cuenta.getSaldoCuenta() >= monto) {
			cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() - monto);

		}

	}

	@Transactional
	public void depositar(Cuenta cuenta, double monto) {
		cuenta.setSaldoCuenta(cuenta.getSaldoCuenta() + monto);

	}

}
