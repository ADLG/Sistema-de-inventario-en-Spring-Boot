package com.sistema.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.entity.Rol;
import com.sistema.inventario.entity.Usuario;
import com.sistema.inventario.repository.RepoRol;
import com.sistema.inventario.repository.RepoUsuario;

@Controller
public class CtrlUsuario
{
	@Autowired
	private RepoUsuario repoUsuario;

	@Autowired
	private RepoRol repoRol;
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model modelo)
	{
		List<Usuario> usuarios = repoUsuario.findAll();
		modelo.addAttribute("usuarios",usuarios);
		return "usuarios";
	}

	@GetMapping("/usuarios/nuevo")
	public String mostrarFormularioRegistroUsuario(Model modelo)
	{
		List<Rol> roles = repoRol.findAll();
		modelo.addAttribute("roles",roles);
		modelo.addAttribute("usuario",new Usuario());
		return "usuario_formulario";
	}

	@PostMapping("/usuarios/guardar")
	public String guardarUsuario(Usuario usuario)
	{
		repoUsuario.save(usuario);
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/editar/{id}")
	public String mostrarFormularioEditarUsuario(@PathVariable("id") Integer id, Model modelo)
	{
		Usuario usuario = repoUsuario.findById(id).get();
		modelo.addAttribute("usuario",usuario);

		List<Rol> roles = repoRol.findAll();
		modelo.addAttribute("roles",roles);
		return  "usuario_formulario";
	}

	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarUsuario(@PathVariable("id") Integer id, Model modelo)
	{
		repoUsuario.deleteById(id);
		return "redirect:/usuarios";
	}
}
