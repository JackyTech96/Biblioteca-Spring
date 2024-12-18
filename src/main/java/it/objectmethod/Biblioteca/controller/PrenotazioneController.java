package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.PrenotazioneDto;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<PrenotazioneDto>> createPrenotazione(@RequestBody final PrenotazioneDto prenotazioneDto) {
        ApiResponse<PrenotazioneDto> response = prenotazioneService.createPrenotazione(prenotazioneDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public PrenotazioneDto updatePrenotazione(@PathVariable final Long id, @RequestBody final PrenotazioneDto prenotazioneDto) {
        return prenotazioneService.updatePrenotazione(id, prenotazioneDto);
    }
}
