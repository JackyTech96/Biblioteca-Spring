package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.UtenteDto;

import it.objectmethod.Biblioteca.pageable.PagedResponse;
import it.objectmethod.Biblioteca.param.UtenteParams;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.UtenteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/multiple")
    public ResponseEntity<ApiResponse<List<UtenteDto>>> creaMultipleUtentiConPersona(@RequestBody final List<UtenteDto> utenteDtoList) {
        ApiResponse<List<UtenteDto>> response = utenteService.creaMultipleUtentiConPersona(utenteDtoList);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UtenteDto findUtenteById(@PathVariable final Long id) {
        return utenteService.findById(id);
    }

    @GetMapping("/spec")
    public List<UtenteDto> getUtenteBySpec(@Valid final UtenteParams params) {
        return utenteService.findWithSpecification(params);
    }

    @GetMapping("/pageable")
    public PagedResponse<UtenteDto> searchUtenti(@PageableDefault final Pageable pageable, @Valid final UtenteParams params) {
        return utenteService.searchUtenti(pageable, params);
    }
}
