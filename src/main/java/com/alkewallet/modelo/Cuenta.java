package com.alkewallet.modelo;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
/**
 * Entidad de Negocio de la Cuenta donde encuentran el titular, saldo 
 * y tambien la creacion de la base de datos
 */
@Entity
public class Cuenta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Cuenta;
	
	//@NotEmpty(message = "***Requerido***")
	private String titularCuenta;
	
	//@NotEmpty(message = "***Requerido***")
	private double saldoCuenta;
	
	@OneToOne(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Cliente cliente;

	public Cuenta(String titularCuenta, double saldoCuenta) {
		super();
		this.titularCuenta = titularCuenta;
		this.saldoCuenta = saldoCuenta;
	}

	public Cuenta() {
		super();
	}

	public Long getId_Cuenta() {
		return id_Cuenta;
	}

	public void setId_Cuenta(Long id_Cuenta) {
		this.id_Cuenta = id_Cuenta;
	}

	public String getTitularCuenta() {
		return titularCuenta;
	}

	public void setTitularCuenta(String titularCuenta) {
		this.titularCuenta = titularCuenta;
	}

	public double getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	

}
