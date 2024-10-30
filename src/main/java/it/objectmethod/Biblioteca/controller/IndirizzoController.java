package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.IndirizzoDto;
import it.objectmethod.Biblioteca.service.IndirizzoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/indirizzo")
public class IndirizzoController {
    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping("")
    public List<IndirizzoDto> findAll() {
        return indirizzoService.getAllIndirizzo();
    }

    @PostMapping("")
    public IndirizzoDto createIndirizzo(@Valid @RequestBody final IndirizzoDto indirizzoDto) {
        return indirizzoService.createIndirizzo(indirizzoDto);
    }
}
