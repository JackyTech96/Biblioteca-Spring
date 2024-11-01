package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public ResponseEntity<PersonaDto> createPersona(@Valid @RequestBody final PersonaDto personaDto) {
        PersonaDto createdPersona = personaService.createPersona(personaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPersona);
    }
}
