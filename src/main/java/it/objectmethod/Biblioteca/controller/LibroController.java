package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.param.LibroParams;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/libro")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<LibroDto>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(libroService.getAllLibri());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LibroDto>> findById(@PathVariable final Long id) {
        ApiResponse<LibroDto> response = libroService.getLibroById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<LibroDto>> createLibro(@Valid @RequestBody final LibroDto libroDto) {
        ApiResponse<LibroDto> response = libroService.createLibro(libroDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public LibroDto updateLibro(@Valid @RequestBody final LibroDto libroDto, @PathVariable final Long id) {
        return libroService.updateLibro(libroDto, id);
    }

    @GetMapping("/spec")
    public List<LibroDto> getLibroBySpec(@Valid final LibroParams params) {
        return libroService.findWithSpecification(params);
    }
}
