package com.sistema.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.inventario.entity.ArticuloCarrito;

public interface RepoArticuloCarrito extends JpaRepository<ArticuloCarrito, Integer>{

}
