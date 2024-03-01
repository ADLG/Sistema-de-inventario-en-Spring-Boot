package com.sistema.inventario;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.entity.ArticuloCarrito;
import com.sistema.inventario.entity.Producto;
import com.sistema.inventario.entity.Usuario;
import com.sistema.inventario.repository.RepoArticuloCarrito;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ArticuloCarritoTest
{
	@Autowired
	private RepoArticuloCarrito repoCarrito;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testAñadirArticulo()
	{
		Producto producto = entityManager.find(Producto.class, 10);
		Usuario usuario = entityManager.find(Usuario.class, 1);
		ArticuloCarrito articulo = new ArticuloCarrito(3,producto,usuario);
		repoCarrito.save(articulo);
	}

	@Test
	public void testAñadirMultiplesArticulos()
	{
		Usuario usuario = new Usuario(1);
		Producto producto1 = new Producto(6);
		Producto producto2 = new Producto(8);

		ArticuloCarrito articulo1 = new ArticuloCarrito(3,producto1,usuario);
		ArticuloCarrito articulo2 = new ArticuloCarrito(5,producto2,usuario);

		repoCarrito.saveAll(List.of(articulo1,articulo2));
	}

	@Test
	public void testListaArticulos()
	{
		List<ArticuloCarrito> articulos = repoCarrito.findAll();
		articulos.forEach(System.out::println);
	}

	@Test
	public void testActualizarCarrito()
	{
		ArticuloCarrito articulo = repoCarrito.findById(1).get();
		articulo.setCantidad(10);
		articulo.setProducto(new Producto(10));
	}

	@Test
	public void testEliminarArticulo()
	{
		repoCarrito.deleteById(5);
	}
}
