package com.azu.lista.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.azu.lista.entidades.Persona;
import com.azu.lista.servicio.PersonaServicio;

@Controller
public class PersonaControlador {
	
	@Autowired
	private PersonaServicio personaServicio;
		
	@RequestMapping("/")
	public String verPaginaInicio(Model modelo, @Param("palabraClave") String palabraClave) {
		List<Persona> listaPersonas = personaServicio.listAll(palabraClave);
				
		modelo.addAttribute("listaPersonas", listaPersonas);
		modelo.addAttribute("palabraClave", palabraClave);
		
		return "index";
	}
	
	@RequestMapping("/nuevo")
	public String mostrarFormularioRegistroPersona(Model modelo) {
		Persona persona = new Persona();
		modelo.addAttribute("persona", persona);
		
		return "nueva_persona";
		
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardarPersona(@ModelAttribute("persona") Persona persona) {
		personaServicio.save(persona);
		
		return "redirect:/";
		
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView mostrarFormularioDeEditarPersona(@PathVariable(name = "id") Long id) {
		
		ModelAndView modelo = new ModelAndView("editar_persona");
		
		Persona persona = personaServicio.get(id);
		
		modelo.addObject("persona", persona);
		
		return modelo;
		
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminarPersona(@PathVariable(name = "id") Long id) {
		personaServicio.delete(id);
		
		return "redirect:/";
	}
}
