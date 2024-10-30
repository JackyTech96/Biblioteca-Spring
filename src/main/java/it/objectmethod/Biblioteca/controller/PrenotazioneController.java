package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PrenotazioneDto;
import it.objectmethod.Biblioteca.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/prenotazione")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("")
    public List<PrenotazioneDto> findAll() {
        return prenotazioneService.getAllPrenotazione();
    }

    @PostMapping("")
    public PrenotazioneDto createPrenotazione(@RequestBody final PrenotazioneDto prenotazioneDto) {
        return prenotazioneService.createPrenotazione(prenotazioneDto);
    }

    @PutMapping("/{id}")
    public PrenotazioneDto updatePrenotazione(@PathVariable final Long id, @RequestBody final PrenotazioneDto prenotazioneDto) {
        return prenotazioneService.updatePrenotazione(id, prenotazioneDto);
    }
}
