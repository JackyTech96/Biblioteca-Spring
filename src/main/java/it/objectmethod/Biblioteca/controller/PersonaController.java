package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/persona")
@CrossOrigin
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping("")
    public List<PersonaDto> getAllPersona() {
        return personaService.findAll();
    }
}