package com.sistema.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.entity.Categoria;
import com.sistema.inventario.entity.Producto;
import com.sistema.inventario.repository.RepoCategoria;
import com.sistema.inventario.repository.RepoProducto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CtrlProducto
{
	@Autowired
	RepoProducto repoProducto;

	@Autowired
	RepoCategoria repoCategoria;

	
	@GetMapping("/productos/nuevo")
	public String mostrarFormularioDeNuevoProducto(Model modelo)
	{
		List<Categoria> categorias = repoCategoria.findAll();

		modelo.addAttribute("producto", new Producto());
		modelo.addAttribute("categorias", categorias);

		return "producto_formulario";
	}
	
	@PostMapping("/productos/guardar")
	public String guardarProducto(Producto producto, HttpServletRequest request)
	{
		String [] detallesIDs = request.getParameterValues("detallesIDs");
		String [] detallesNombres = request.getParameterValues("detallesNombres");
		String [] detallesValores = request.getParameterValues("detallesValores");

		for (int i=0; i<detallesValores.length; i++)
		{
			if (detallesIDs != null && detallesIDs.length > 0)
			{
				producto.setDetalles(Integer.valueOf(detallesIDs[i]),detallesNombres[i],detallesValores[i]);
			}
			else
			{
				producto.agregarDetalles(detallesNombres[i],detallesValores[i]);
			}
		}

		repoProducto.save(producto);
		return "redirect:/";
	}

	@GetMapping("/productos")
	public String listarProductos(Model modelo)
	{
		List<Producto> productos = repoProducto.findAll();
		modelo.addAttribute("productos",productos);

		return "productos";
	}

	@GetMapping("/productos/editar/{id}")
	public String mostrarFormularioDeModificarProducto(@PathVariable("id") Integer id, Model modelo)
	{
		Producto producto = repoProducto.findById(id).get();
		modelo.addAttribute("producto",producto);

		List<Categoria> categorias = repoCategoria.findAll();
		modelo.addAttribute("categorias",categorias);

		return "producto_formulario";
	}

	@GetMapping("/productos/eliminar/{id}")
	public String elminarProducto(@PathVariable("id") Integer id, Model modelo)
	{
		repoProducto.deleteById(id);
		return "redirect:/productos";
	}
}
