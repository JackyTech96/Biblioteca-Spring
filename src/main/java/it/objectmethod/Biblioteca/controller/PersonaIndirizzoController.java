package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PersonaIndirizzoDto;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.PersonaIndirizzoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/personaIndirizzo")
public class PersonaIndirizzoController {
    @Autowired
    private PersonaIndirizzoService personaIndirizzoService;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<List<PersonaIndirizzoDto>>> getPersonaWithIndirizzo(@Valid @PathVariable final Long id) {
        ApiResponse<List<PersonaIndirizzoDto>> response = personaIndirizzoService.getPersonaWithIndirizzo(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
