package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.LibroMapper;
import it.objectmethod.Biblioteca.param.LibroParams;
import it.objectmethod.Biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public LibroDto updateLibro(final LibroDto libroDto, final Long id) {
        Libro libro = libroRepository.getById(id);
        if (libro == null) {
            throw new ElementNotFoundException("Libro non trovato");
        }
        libroMapper.updateLibro(libro, libroDto);
        return libroMapper.libroToLibroDto(libroRepository.save(libro));
    }


    /**
     * Cancella i libri che non hanno isbn o che non contengono l'anno di pubblicazione
     * nell'isbn.
     */
    public void sanitizeLibriByIsbn() {
        // Recupera tutti i libri dal database
        List<Libro> libri = libroRepository.findAll();
        // Filtra i libri che non hanno isbn o che non contengono l'anno di pubblicazione
        // nell'isbn
        List<Libro> libriToDelete = libri.stream()
                .filter(libro -> libro.getIsbn() == null || !libro.getIsbn().contains(
                        String.valueOf(libro.getAnnoPubblicazione().getValue())))
                .collect(Collectors.toList());
        // Elimina i libri che non soddisfano le condizioni
        libriToDelete.forEach(libro -> libroRepository.delete(libro));
        // Stampa il numero di libri eliminati
        System.out.println("Libri non validi eliminati: " + libriToDelete.size());
    }
}
