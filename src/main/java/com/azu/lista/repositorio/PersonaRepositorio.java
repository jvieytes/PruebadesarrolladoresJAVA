package com.azu.lista.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.azu.lista.entidades.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Long>{
	
	@Query("SELECT p FROM Persona p WHERE" + " CONCAT(p.id,p.nombre,p.apellido,p.edad)" + " LIKE %?1%")
	public List<Persona> findAll(String palabraClave);
	
	public Persona findByNombre(String nombre);
}
