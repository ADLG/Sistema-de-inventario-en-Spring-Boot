package com.sistema.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.entity.Categoria;
import com.sistema.inventario.repository.RepoCategoria;

@Controller
public class CtrlCategoria
{
	@Autowired
	private RepoCategoria repoCategoria;
	
	@GetMapping("/categorias")
	public String listarCategorias(Model modelo)
	{
		List<Categoria> categorias = repoCategoria.findAll();
		modelo.addAttribute("categorias",categorias);
		return "categorias";
	}
	
	@GetMapping("/categorias/nuevo")
	public String mostrarFormularioNuevaCategoria(Model modelo)
	{
		modelo.addAttribute("categoria", new Categoria());
		return "categoria_formulario";
	}

	@PostMapping("/categorias/guardar")
	public String guardarCategoria(Categoria categoria)
	{
		repoCategoria.save(categoria);
		return "redirect:/categorias";
	}
}
