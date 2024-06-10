package com.alkewallet.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkewallet.modelo.Transaccion;

@Repository
public interface TransaccionRepositorio extends JpaRepository<Transaccion, Long> {

}
