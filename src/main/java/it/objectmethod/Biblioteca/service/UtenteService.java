package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.UtenteDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.entity.Utente;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.excepction.OperationNotAllowedException;
import it.objectmethod.Biblioteca.mapper.PagedMapper;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.mapper.UtenteMapper;
import it.objectmethod.Biblioteca.pageable.PagedResponse;
import it.objectmethod.Biblioteca.param.UtenteParams;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.repository.UtenteRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private PagedMapper pagedMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UtenteDto> findAll() {
        return utenteMapper.utenteListToUtenteDtoList(utenteRepository.findAll());
    }


    /**
     * Creazione di un utente con relativa persona
     *
     * @param utenteDto il dto dell'utente da creare
     * @return il dto dell'utente creato
     */
    @Transactional
    public ApiResponse<UtenteDto> creaUtenteConPersona(final UtenteDto utenteDto) {

//        PersonaDto personaDto = utenteDto.getPersona();
        Persona persona = new Persona();
        persona.setNome(utenteDto.getNome());
        persona.setEmail(utenteDto.getEmail());
        persona.setTelefono(utenteDto.getTelefono());
        persona.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
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
            UtenteDto utenteDtoSalvato = utenteMapper.utenteToUtenteDto(utente);
            return new ApiResponse<>("Operazione riuscita", utenteDtoSalvato);
        } catch (RuntimeException e) {
            throw new OperationNotAllowedException("Operazione di salvataggio non riuscita");
        }
    }

    public ApiResponse<List<UtenteDto>> creaMultipleUtentiConPersona(List<UtenteDto> utenteDtoList) {
        for (UtenteDto utenteDto : utenteDtoList) {
            try {
                creaUtenteConPersona(utenteDto);
            } catch (Exception e) {
                throw new OperationNotAllowedException("Operazione di creazione non riuscita");
            }
        }
        return new ApiResponse<>("Operazione riuscita", utenteDtoList);
    }

    public UtenteDto findById(Long utenteId) {
        try {
            return utenteMapper.utenteToUtenteDto(utenteRepository.findById(utenteId).get());
        } catch (Exception e) {
            throw new ElementNotFoundException("Utente non trovato");
        }
    }

    public List<UtenteDto> findWithSpecification(final UtenteParams utenteParams) {
        List<Utente> utenti = utenteRepository.findAll(utenteParams.toSpecification());
        if (utenti.isEmpty()) {
            throw new ElementNotFoundException("Nessun utente trovato con i criteri specificati.");
        }
        return utenteMapper.utenteListToUtenteDtoList(utenti);
    }

    public PagedResponse<UtenteDto> searchUtenti(Pageable pageable, UtenteParams utenteParams) {
        // Crea la Specification dalla classe UtenteParams
        Specification<Utente> specification = utenteParams.toSpecification();

        // Recupera la pagina con i criteri specificati e la paginazione
        Page<Utente> utenti = utenteRepository.findAll(specification, pageable);

        // Crea la lista di utenti da restituire
        List<UtenteDto> utenteDtoList = utenti.getContent().stream()
                .map(utenteMapper::utenteToUtenteDto)
                .toList();

        // Crea la risposta con la lista di utenti e la paginazione
        return pagedMapper.toPagedResponse(utenteDtoList, utenti.getNumber(), utenti.getSize());
        //TODO: creare il mapper per fare in modo di risolvere la mappattura per ogni oggetto da restituire
    }

//    public Page<UtenteDto> searchUtenti(Pageable pageable, UtenteParams utenteParams) {
//        Specification<Utente> specification = utenteParams.toSpecification();
//
//        return utenteRepository.findAll(specification, pageable).map(utenteMapper::utenteToUtenteDto);
//    }

//    public PagedResponse<UtenteDto> searchUtenti(Pageable pageable) {
//        Page<Utente> utenti = utenteRepository.findAll(pageable);
//        return new PagedResponse<>(utenti.map(utenteMapper::utenteToUtenteDto));
//    }

//    public Page<UtenteDto> searchUtenti(Pageable pageable) {
//        Page<Utente> utenti = utenteRepository.findAll(pageable);
//        return utenti.map(utente -> utenteMapper.utenteToUtenteDto(utente));
//    }
}
