package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PersonaleDto;
import it.objectmethod.Biblioteca.param.PersonaleParams;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.PersonaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personale")
@CrossOrigin
public class PersonaleController {

    @Autowired
    private PersonaleService personaleService;

    @GetMapping("")
    public List<PersonaleDto> findAll() {
        return personaleService.getAllPersonale();
    }

    @PostMapping("")

    public ResponseEntity<ApiResponse<PersonaleDto>> createPersonaleConPersona(@Valid @RequestBody final PersonaleDto personaleDto) {
        ApiResponse<PersonaleDto> response = personaleService.createPersonaleConPersona(personaleDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PersonaleDto findPersonaleById(@Valid @PathVariable final Long id) {
        return personaleService.findPersonaleById(id);
    }

    @GetMapping("/ruolo/{nomeRuolo}")
    public List<PersonaleDto> findPersonaleByNomeRuolo(@Valid @PathVariable final String nomeRuolo) {
        return personaleService.findPersonaleByNomeRuolo(nomeRuolo);
    }

    @GetMapping("/spec")
    public List<PersonaleDto> getPersonaleBySpec(@Valid final PersonaleParams params) {
        return personaleService.findWithSpecification(params);
    }
}
