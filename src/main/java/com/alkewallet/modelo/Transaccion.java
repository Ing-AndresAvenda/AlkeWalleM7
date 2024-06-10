package com.alkewallet.modelo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
/**
 * Entidad de Negocio encargada de la fecha, monto, cuentaOrigen y destino, de administrar las transacciones
 */
@Entity
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime fechaTransaccion;
	private String tipo;
	@NotEmpty(message = "***Requerido***")
	private Double monto;
	@NotEmpty(message = "***Requerido***")
	private Long cuentaOrigen;
	@NotEmpty(message = "***Requerido***")
	private Long cuentaDestino;

	public Transaccion(LocalDateTime fechaTransaccion, String tipo, Double monto, Long cuentaOrigen,
			Long cuentaDestino) {
		super();
		this.fechaTransaccion = fechaTransaccion;
		this.tipo = tipo;
		this.monto = monto;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
	}

	public Transaccion() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Long getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Long cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Long getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Long cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

}
