package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.LibroMapper;
import it.objectmethod.Biblioteca.param.LibroParams;
import it.objectmethod.Biblioteca.repository.LibroRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private LibroMapper libroMapper;
    @Autowired
    ExcelExportService excelExportService;

    public List<LibroDto> getAllLibri() {
        return libroMapper.libriToLibroDto(libroRepository.findAll());
    }

    public ApiResponse<LibroDto> createLibro(final LibroDto libroDto) {
        Libro libro = libroMapper.libroDtoToLibro(libroDto);
        LibroDto libroDtoToSave = libroMapper.libroToLibroDto(libroRepository.save(libro));
        return new ApiResponse<>("Libro creato con successo", libroDtoToSave);
    }

    public List<LibroDto> findWithSpecification(final LibroParams libroParams) {
        return Optional.of(libroMapper.libriToLibroDto(libroRepository.findAll(libroParams.toSpecification())))
                .stream()
                .filter(List::isEmpty)
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException("Nessun libro trovato con i criteri specificati"));
    }

    public LibroDto updateLibro(final LibroDto libroDto, final Long id) {
        Libro libro = libroRepository.findById(id).orElseThrow(
                () -> new ElementNotFoundException("Nessun libro trovato con l'id " + id));
        libroMapper.updateLibro(libro, libroDto);
        if (libro.getIsbn() == null || !libro.getIsbn().contains(String.valueOf(libro.getAnnoPubblicazione().getValue()))) {
            throw new ElementNotFoundException("L'isbn del libro non contiene l'anno di pubblicazione");
        }
        return libroMapper.libroToLibroDto(libroRepository.save(libro));
    }


    /**
     * Cancella i libri che non hanno ISBN o che non contengono l'anno di pubblicazione
     * nell'ISBN e crea un backup in Excel prima della sanificazione.
     */

    public void sanitizeLibriByIsbn() {
        // Recupera tutti i libri dal database
        List<Libro> libri = libroRepository.findAll();

        try {
            // Esporta tutti i dati dei libri in un file Excel come copia di backup
            excelExportService.exportLibrosToExcel();
            System.out.println("Backup dei libri eseguito con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante l'esportazione dei libri: " + e.getMessage());
            return; // Esci se non è possibile effettuare il backup
        }

        // Filtra i libri che non rispettano le condizioni
        List<Libro> libriToDelete = libri.stream()
                .filter(libro -> libro.getIsbn() == null ||
                        !libro.getIsbn().contains(String.valueOf(libro.getAnnoPubblicazione().getValue())))
                .toList();

        // Elimina i libri non validi
        libroRepository.deleteAll(libriToDelete);

        // Stampa il numero di libri eliminati
        System.out.println("Libri non validi eliminati: " + libriToDelete.size());
    }

}
