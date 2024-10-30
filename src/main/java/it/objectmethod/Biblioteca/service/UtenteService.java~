package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.UtenteDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.entity.Utente;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.excepction.OperationNotAllowedException;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.mapper.UtenteMapper;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private UtenteMapper utenteMapper;

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;

    public List<UtenteDto> findAll() {
        return utenteMapper.utenteListToUtenteDtoList(utenteRepository.findAll());
    }


    /**
     * Creazione di un utente con relativa persona
     *
     * @param utenteDto il dto dell'utente da creare
     * @return il dto dell'utente creato
     */
    public UtenteDto creaUtenteConPersona(UtenteDto utenteDto) {

//        PersonaDto personaDto = utenteDto.getPersona();
        Persona persona = new Persona();
        persona.setNome(utenteDto.getNome());
        persona.setEmail(utenteDto.getEmail());
        persona.setTelefono(utenteDto.getTelefono());
        persona = personaRepository.save(persona);

        // Imposto la data di inizio iscrizione e fine iscrizione
        Date inizioIscrizione = new Date();
        utenteDto.setInizioIscrizione(inizioIscrizione);
        LocalDate fineIscrizioneLocalDate = LocalDate.now().plusYears(1);

        // Converto la LocalDate in Date utilizzando la zona di default del sistema
        // e impostando l'ora all'inizio del giorno
        Date fineIscrizione = Date.from(fineIscrizioneLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        utenteDto.setFineIscrizione(fineIscrizione);
        try {
            Utente utente = utenteMapper.utenteDtoToUtente(utenteDto);
            utente.setPersona(persona);
            utenteRepository.save(utente);
            return utenteMapper.utenteToUtenteDto(utente);
        } catch (RuntimeException e) {
            throw new OperationNotAllowedException("Operazione di salvataggio non riuscita");
        }
    }

    public UtenteDto findById(Long utenteId) {
        try {
            return utenteMapper.utenteToUtenteDto(utenteRepository.findById(utenteId).get());
        } catch (Exception e) {
            throw new ElementNotFoundException("Utente non trovato");
        }
    }
}
