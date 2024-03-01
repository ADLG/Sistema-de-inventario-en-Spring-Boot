package com.sistema.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.inventario.entity.Categoria;

public interface RepoCategoria extends JpaRepository<Categoria, Integer>
{

}
