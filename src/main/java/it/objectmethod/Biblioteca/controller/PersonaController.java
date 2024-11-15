package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PersonaDto;
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
                .body(new ApiResponse<>("Lista persone", personaService.findAll()));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<PersonaDto>> createPersona(@Valid @RequestBody final PersonaDto personaDto) {
        ApiResponse<PersonaDto> response = personaService.createPersona(personaDto);
        response.setMessage("Persona creata correttamente");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
