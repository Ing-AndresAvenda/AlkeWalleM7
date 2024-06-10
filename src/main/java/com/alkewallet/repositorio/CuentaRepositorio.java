package com.alkewallet.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkewallet.modelo.Cuenta;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long>{

}
