package com.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@teste.com", "12345678", "url01"));
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@teste.com", "12345678", "url02"));
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@teste.com", "12345678", "url03"));
		usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@teste.com", "12345678", "url04"));
	}
	
	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@teste.com");
		assertTrue(usuario.get().getUsuario().equals("joao@teste.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuários")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}
}
