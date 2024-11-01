package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.PrenotazioneDto;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.entity.Prenotazione;
import it.objectmethod.Biblioteca.entity.Utente;
import it.objectmethod.Biblioteca.enums.StatoPrenotazione;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.LibroMapper;
import it.objectmethod.Biblioteca.mapper.PrenotazioneMapper;
import it.objectmethod.Biblioteca.repository.LibroRepository;
import it.objectmethod.Biblioteca.repository.PrenotazioneRepository;
import it.objectmethod.Biblioteca.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private PrenotazioneMapper prenotazioneMapper;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private UtenteRepository utenteRepository;

    public List<PrenotazioneDto> getAllPrenotazione() {
        return prenotazioneMapper.prenotazioneListToPrenotazioneDtoList(prenotazioneRepository.findAll());
    }

    public PrenotazioneDto createPrenotazione(final PrenotazioneDto prenotazioneDto) {
        prenotazioneDto.setDataPrenotazione(new Date());
        prenotazioneDto.setStato(StatoPrenotazione.ATTIVA);
        Prenotazione prenotazione = prenotazioneMapper.prenotazioneDtoToPrenotazione(prenotazioneDto);
        return prenotazioneMapper.prenotazioneToPrenotazioneDto(prenotazioneRepository.save(prenotazione));
    }

    public PrenotazioneDto updatePrenotazione(final Long id, final PrenotazioneDto prenotazioneDto) {
        // Controlla se la prenotazione esiste
        if (!prenotazioneRepository.existsById(id)) {
            throw new ElementNotFoundException("Prenotazione non trovata");
        }

        // Crea una nuova prenotazione esistente per l'aggiornamento
        Prenotazione prenotazioneExist = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Prenotazione non trovata"));

        // Aggiorna i campi della prenotazione esistente
        prenotazioneExist.setDataPrenotazione(prenotazioneDto.getDataPrenotazione());
        prenotazioneExist.setStato(prenotazioneDto.getStato());

        // Aggiornamento del libro se l'ID è fornito
        if (prenotazioneDto.getLibroId() != null) {
            Optional<Libro> libro = libroRepository.findById(prenotazioneDto.getLibroId());
            if (libro.isPresent()) {
                prenotazioneExist.setLibro(libro.get());
            } else {
                throw new ElementNotFoundException("Libro non trovato");
            }
        }

        // Aggiornamento dell'utente se l'ID è fornito
        if (prenotazioneDto.getUtenteId() != null) {
            Optional<Utente> utente = utenteRepository.findById(prenotazioneDto.getUtenteId());
            if (utente.isPresent()) {
                prenotazioneExist.setUtente(utente.get());
            } else {
                throw new ElementNotFoundException("Utente non trovato");
            }
        }

        // Salva e restituisci la prenotazione aggiornata
        return prenotazioneMapper.prenotazioneToPrenotazioneDto(prenotazioneRepository.save(prenotazioneExist));
    }

}
