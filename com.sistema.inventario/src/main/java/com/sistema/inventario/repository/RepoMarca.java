package com.sistema.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.inventario.entity.Marca;

public interface RepoMarca extends JpaRepository<Marca, Integer>{

}
