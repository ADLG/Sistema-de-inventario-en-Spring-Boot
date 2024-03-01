package com.sistema.inventario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.entity.Rol;
import com.sistema.inventario.entity.Usuario;
import com.sistema.inventario.repository.RepoUsuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RepoUsuarioTest
{
	@Autowired
	private RepoUsuario repoUsuario;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCrearRoles()
	{
		Rol rolAdmin = new Rol("Administrador");
		Rol rolEditor = new Rol("Editor");
		Rol rolVisitante = new Rol("Visitante");
		
		entityManager.persist(rolAdmin);
		entityManager.persist(rolEditor);
		entityManager.persist(rolVisitante);
	}

	@Test
	public void testCrearNuevoUsuarioConRol()
	{
		Rol rolAdmin = entityManager.find(Rol.class,1);
		Usuario usuario = new Usuario("java@gmail.com","12345");

		usuario.añadirRol(rolAdmin);
		repoUsuario.save(usuario);
	}
	
	@Test
	public void testCrearNuevoUsuarioConDosRoles()
	{
		Rol rolEditor = entityManager.find(Rol.class,2);
		Rol rolVisitante = entityManager.find(Rol.class,3);
		Usuario usuario = new Usuario("ruby@gmail.com","123451");

		usuario.añadirRol(rolEditor);
		usuario.añadirRol(rolVisitante);
		repoUsuario.save(usuario);
	}

	@Test
	public void testAsignarRolUsuarioExistente()
	{
		Usuario usuario = repoUsuario.findById(1).get();
		Rol rolEditor = entityManager.find(Rol.class,2);

		usuario.añadirRol(rolEditor);
	}

	@Test
	public void testEliminarRolDeUsuarioExsitente()
	{
		Usuario usuario = repoUsuario.findById(1).get();
		Rol rol = new Rol(2);
		usuario.eliminarRol(rol);
	}

	@Test
	public void crearNuevoUsuarioConNuevoRol()
	{
		Rol rolVendedor = new Rol("Vendedor");
		Usuario usuario = new Usuario("venta@gmail.com","223452");

		usuario.añadirRol(rolVendedor);
		repoUsuario.save(usuario);
	}

	@Test
	public void testObtenerUsuario()
	{
		Usuario usuario = repoUsuario.findById(1).get();
		entityManager.detach(usuario);

		System.out.println(usuario.getEmail());
		System.out.println(usuario.getRoles());
	}

	@Test
	public void eliminarUsuario()
	{
		repoUsuario.deleteById(2);

	}
}
