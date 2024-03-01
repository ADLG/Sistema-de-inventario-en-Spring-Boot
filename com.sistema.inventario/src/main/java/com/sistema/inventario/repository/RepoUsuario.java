package com.sistema.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.inventario.entity.Usuario;

public interface RepoUsuario extends JpaRepository<Usuario, Integer>{

}
