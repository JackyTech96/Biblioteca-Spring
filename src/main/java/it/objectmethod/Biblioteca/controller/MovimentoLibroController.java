package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.MovimentoLibroDto;
import it.objectmethod.Biblioteca.entity.MovimentoLibro;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.MovimentoLibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimentoLibro")
public class MovimentoLibroController {
    @Autowired
    private MovimentoLibroService movimentoLibroService;

    @GetMapping("")
    public List<MovimentoLibroDto> getAllMovimentoLibro() {
        return movimentoLibroService.getAllMovimentoLibro();
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<MovimentoLibroDto>> createMovimentoLibro(@RequestBody final MovimentoLibroDto movimentoLibroDto) {
        ApiResponse<MovimentoLibroDto> response = movimentoLibroService.createMovimentoLibro(movimentoLibroDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
