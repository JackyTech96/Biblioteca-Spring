package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.MovimentoLibroDto;
import it.objectmethod.Biblioteca.service.MovimentoLibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MovimentoLibroDto createMovimentoLibro(@Valid @RequestBody final MovimentoLibroDto movimentoLibroDto) {
        return movimentoLibroService.createMovimentoLibro(movimentoLibroDto);
    }

}
