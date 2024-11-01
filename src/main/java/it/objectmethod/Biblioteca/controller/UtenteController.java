package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.UtenteDto;

import it.objectmethod.Biblioteca.param.UtenteParams;
import it.objectmethod.Biblioteca.service.UtenteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/utente")
@CrossOrigin
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    public List<UtenteDto> getAllUtente() {
        return utenteService.findAll();
    }

    @PostMapping("")
    public UtenteDto creaUtenteConPersona(@RequestBody final UtenteDto utenteDto) {
        return utenteService.creaUtenteConPersona(utenteDto);
    }

    @GetMapping("/{id}")
    public UtenteDto findUtenteById(@PathVariable final Long id) {
        return utenteService.findById(id);
    }

    @GetMapping("/spec")
    public List<UtenteDto> getUtenteBySpec(@Valid final UtenteParams params) {
        return utenteService.findWithSpecification(params);
    }
}
