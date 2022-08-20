package com.azu.lista;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.azu.lista.entidades.Persona;
import com.azu.lista.repositorio.PersonaRepositorio;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonaTest {
	
	@Autowired
	private PersonaRepositorio repositorio;
	
	@Test
	@Rollback(false)	
	public void testIngresarPersona() {
		Persona persona = new Persona(6L,"Albert","Wesker",50);
		Persona personaIngresada = repositorio.save(persona);
		
		assertNotNull(personaIngresada);
	}
	
	@Test
	public void testBuscarPersona() {
		
		String nombre = "Albert";
		
		Persona persona = repositorio.findByNombre(nombre);
		
		assertThat(persona.getNombre()).isEqualTo(nombre);
		
	}
	
	@Test
	public void testBuscarPersonaNoExiste() {
		
		String nombre = "Juanito";
		
		Persona persona = repositorio.findByNombre(nombre);
		
		assertNull(persona);
		
	}
	
	@Test
	@Rollback(false)
	public void testEditarPersona() {
		String nombrePersona = "Pedro";
		Persona persona = new Persona(4L,nombrePersona,"Redfield",25);
		persona.setId(4L);
		
		repositorio.save(persona);
		
		Persona personaEditada = repositorio.findByNombre(nombrePersona);
		assertThat(personaEditada.getNombre()).isEqualTo(nombrePersona);
	}
	
	@Test
	public void testListarPersonas() {
		List<Persona> personas = (List<Persona>) repositorio.findAll();
		
		assertThat(personas).size().isGreaterThan(0);
	}
	
	@Test
	@Rollback(false)
	public void testEliminarPersona() {
		Long id = 6L;
		
		boolean esExistenteAntesDeEliminar = repositorio.findById(id).isPresent();
		
		repositorio.deleteById(id);
		
		boolean noExisteDespuesDeEliminar = repositorio.findById(id).isPresent();
		assertTrue(esExistenteAntesDeEliminar);
		assertFalse(noExisteDespuesDeEliminar);
		
	}
}
