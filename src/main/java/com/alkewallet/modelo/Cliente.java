package com.alkewallet.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
/**
 * Entidad de negocio Cliente donde asigamos el id, nombre, apellido
 * y tambien la creacion de las tablas en la base de datos
 * Se encuentra los getter y setter como el constructor 
 */
@Entity
public class Cliente {// ENTIDAD DE NEGOCIO, REFLEJO DE UNA BASE DE DATOS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_Cliente;
	
	@NotEmpty(message = "***Requerido***")
	private String nombreCliente;
	
	@NotEmpty(message = "***Requerido***")
	private String apellidoCliente;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cuenta_id", referencedColumnName = "id_Cuenta")
	private Cuenta cuenta;

	
	
	public Cliente() {
		super();
	}

	public Integer getId_Cliente() {
		return id_Cliente;
	}

	public void setId_Cliente(Integer id_Cliente) {
		this.id_Cliente = id_Cliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	

}
