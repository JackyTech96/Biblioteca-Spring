package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<PersonaDto>>> getAllPersona() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<PersonaDto>> getPersona(@Valid @PathVariable final Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personaService.getPersonaById(id));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<PersonaDto>> createPersona(@Valid @RequestBody final PersonaDto personaDto) {
        ApiResponse<PersonaDto> response = personaService.createPersona(personaDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<PersonaDto>> updatePersona(@Valid @PathVariable final Long id, @Valid @RequestBody final PersonaDto personaDto) {
        ApiResponse<PersonaDto> response = personaService.updatePersona(personaDto, id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
