package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.IndirizzoDto;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.IndirizzoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<IndirizzoDto>> createIndirizzo(@Valid @RequestBody final IndirizzoDto indirizzoDto) {
        ApiResponse<IndirizzoDto> response = indirizzoService.createIndirizzo(indirizzoDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
