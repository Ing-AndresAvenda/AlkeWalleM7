package com.alkewallet.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkewallet.modelo.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long>{

}
