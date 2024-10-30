package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PersonaleDto;
import it.objectmethod.Biblioteca.enums.NomeRuolo;
import it.objectmethod.Biblioteca.service.PersonaleService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PersonaleDto createPersonaleConPersona(@RequestBody final PersonaleDto personaleDto) {
        return personaleService.createPersonaleConPersona(personaleDto);
    }

    @GetMapping("/{id}")
    public PersonaleDto findPersonaleById(@PathVariable final Long id) {
        return personaleService.findPersonaleById(id);
    }

    @GetMapping("/ruolo/{nomeRuolo}")
    public List<PersonaleDto> findPersonaleByNomeRuolo(@PathVariable final NomeRuolo nomeRuolo) {
        return personaleService.findPersonaleByNomeRuolo(nomeRuolo);
    }
}
