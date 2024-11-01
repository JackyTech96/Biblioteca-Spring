package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.LibroMapper;
import it.objectmethod.Biblioteca.param.LibroParams;
import it.objectmethod.Biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private LibroMapper libroMapper;

    public List<LibroDto> getAllLibri() {
        return libroMapper.libriToLibroDto(libroRepository.findAll());
    }

    public LibroDto createLibro(final LibroDto libroDto) {
        Libro libro = libroMapper.libroDtoToLibro(libroDto);
        return libroMapper.libroToLibroDto(libroRepository.save(libro));
    }

    public List<LibroDto> findWithSpecification(final LibroParams libroParams) {
        List<Libro> libri = libroRepository.findAll(libroParams.toSpecification());
        if (libri.isEmpty()) {
            throw new ElementNotFoundException("Nessun libro trovato con i criteri specificati");
        }
        return libroMapper.libriToLibroDto(libri);
    }
}
