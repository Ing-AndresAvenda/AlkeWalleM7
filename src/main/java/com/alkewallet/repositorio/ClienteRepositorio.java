package com.alkewallet.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkewallet.modelo.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
	// PERMITE LA CONEXION CON LA BASE DE DATOS
}
