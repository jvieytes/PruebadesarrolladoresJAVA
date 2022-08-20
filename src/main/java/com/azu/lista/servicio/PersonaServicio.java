package com.azu.lista.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azu.lista.entidades.Persona;
import com.azu.lista.repositorio.PersonaRepositorio;


@Service
public class PersonaServicio {
	
	@Autowired
	private PersonaRepositorio personaRepositorio;
	
	public List<Persona> listAll(String palabraClave){
		
		if(palabraClave != null) {
			return personaRepositorio.findAll(palabraClave);
		}		
		return personaRepositorio.findAll();
	}
	
	public void save(Persona persona) {
		personaRepositorio.save(persona);
	}
	
	public Persona get(Long id) {
		return personaRepositorio.findById(id).get();
	}
	
	public void delete(Long id) {
		personaRepositorio.deleteById(id);
	}
}
