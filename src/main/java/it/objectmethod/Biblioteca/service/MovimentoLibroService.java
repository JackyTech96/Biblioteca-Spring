package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.MovimentoLibroDto;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.entity.MovimentoLibro;
import it.objectmethod.Biblioteca.entity.Utente;
import it.objectmethod.Biblioteca.enums.StatoMovimento;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.MovimentoLibroMapper;
import it.objectmethod.Biblioteca.repository.LibroRepository;
import it.objectmethod.Biblioteca.repository.MovimentoLibroRepository;
import it.objectmethod.Biblioteca.repository.UtenteRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentoLibroService {
    @Autowired
    private MovimentoLibroRepository movimentoLibroRepository;
    @Autowired
    private MovimentoLibroMapper movimentoLibroMapper;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    public List<MovimentoLibroDto> getAllMovimentoLibro() {
        return movimentoLibroMapper.movimentoLibroListToMovimentoLibroDtoList(movimentoLibroRepository.findAll());
    }

    /**
     * crea un nuovo movimento del libro
     *
     * @param movimentoLibroDto movimento del libro da creare
     * @return il movimento del libro creato
     */
    public ApiResponse<MovimentoLibroDto> createMovimentoLibro(final MovimentoLibroDto movimentoLibroDto) {
        // calcola la data di prestito e imposta dataScadenzaRestituzione
        LocalDate dataPrestito = movimentoLibroDto.getDataPrestito().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataScadenza = dataPrestito.plusMonths(1);

        // Imposta la data di scadenza nel DTO prima di mappare
        movimentoLibroDto.setDataScadenzaRestituzione(Date.from(
                dataScadenza.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        //Imposta lo stato di prestito a attivo
        movimentoLibroDto.setStato(StatoMovimento.ATTIVO);
        // Mappa il DTO a un oggetto MovimentoLibro
        MovimentoLibro movimentoLibro = movimentoLibroMapper.movimentoLibroDtoToMovimentoLibro(movimentoLibroDto);

        // cerca il libro
        Optional<Libro> libroOpt = libroRepository.findById(movimentoLibroDto.getLibroId());
        Libro libro = libroOpt.orElseThrow(() -> new ElementNotFoundException("Libro non trovato"));

        // cerca l'utente
        Optional<Utente> utenteOpt = utenteRepository.findById(movimentoLibroDto.getUtenteId());
        Utente utente = utenteOpt.orElseThrow(() -> new ElementNotFoundException("Utente non trovato"));

        // setta il libro e l'utente del movimento
        movimentoLibro.setLibro(libro);
        movimentoLibro.setUtente(utente);

        MovimentoLibroDto movimentoLibroDtoSaved = movimentoLibroMapper
                .movimentoLibroToMovimentoLibroDto(movimentoLibroRepository.save(movimentoLibro));

        return new ApiResponse<>("Movimento creato correttamente", movimentoLibroDtoSaved);
    }

}
