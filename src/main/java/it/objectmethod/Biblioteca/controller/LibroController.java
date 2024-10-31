package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.param.LibroParams;
import it.objectmethod.Biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/libro")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("")
    public List<LibroDto> findAll() {
        return libroService.getAllLibri();
    }

    @PostMapping("")
    public LibroDto createLibro(@Valid @RequestBody final LibroDto libroDto) {
        return libroService.createLibro(libroDto);
    }

    @GetMapping("/spec")
    public List<LibroDto> getLibroBySpec(@Valid final LibroParams params) {
        return libroService.findWithSpecification(params);
    }
}
