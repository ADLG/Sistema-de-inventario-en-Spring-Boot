package com.sistema.inventario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.entity.Categoria;
import com.sistema.inventario.repository.RepoCategoria;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RepoCategoriaTest
{
	@Autowired
	private RepoCategoria repoCategoria;
	
	@Test
	public void testCrearCategoria()
	{
		Categoria categoriaGuardada = repoCategoria.save(new Categoria("Electronicos"));
		assertThat(categoriaGuardada.getId()).isGreaterThan(0);
	}
}
