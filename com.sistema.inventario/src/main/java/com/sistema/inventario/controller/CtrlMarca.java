package com.sistema.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.entity.Categoria;
import com.sistema.inventario.entity.Marca;
import com.sistema.inventario.repository.RepoCategoria;
import com.sistema.inventario.repository.RepoMarca;

@Controller
public class CtrlMarca
{
	@Autowired
	private RepoMarca repoMarca;

	@Autowired

	private RepoCategoria repoCategoria;

	@GetMapping("/marcas/nuevo")
	public String mostrarFormularioNuevaMarca(Model modelo)
	{
		List<Categoria> categorias = repoCategoria.findAll();
		modelo.addAttribute("categorias",categorias);
		modelo.addAttribute("marca", new Marca());
		return "marca_formulario";
	}

	@GetMapping("/marcas/editar/{id}")
	public String mostrarFormularioEditarMarca(@PathVariable("id") Integer id, Model modelo)
	{
		List<Categoria> categorias = repoCategoria.findAll();
		Marca marca = repoMarca.findById(id).get();

		modelo.addAttribute("categorias",categorias);
		modelo.addAttribute("marca", marca);
		return "marca_formulario";
	}

	@PostMapping("/marcas/guardar")
	public String guardarMarca(Marca marca)
	{
		repoMarca.save(marca);
		return "redirect:/";
	}

	@GetMapping("/marcas")
	public String listarMarcas(Model modelo)
	{
		List<Marca> marcas = repoMarca.findAll();
		modelo.addAttribute("marcas",marcas);
		return "marcas";
	}
}
