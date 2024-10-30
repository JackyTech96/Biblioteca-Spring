package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.dto.PersonaleDto;
import it.objectmethod.Biblioteca.dto.RuoloDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.entity.Personale;
import it.objectmethod.Biblioteca.entity.Ruolo;
import it.objectmethod.Biblioteca.enums.NomeRuolo;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.mapper.PersonaleMapper;

import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.repository.PersonaleRepository;
import it.objectmethod.Biblioteca.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaleService {

    @Autowired
    private PersonaleRepository personaleRepository;

    @Autowired
    private PersonaleMapper personaleMapper;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private RuoloRepository ruoloRepository;

    public List<PersonaleDto> getAllPersonale() {
        return personaleMapper.personaleListToPersonaleDtoList(personaleRepository.findAll());
    }

    public PersonaleDto createPersonaleConPersona(final PersonaleDto personaleDto) {
        // Creazione della persona
        Persona persona = new Persona();
        persona.setNome(personaleDto.getNome());
        persona.setEmail(personaleDto.getEmail());
        persona.setTelefono(personaleDto.getTelefono());
        persona = personaRepository.save(persona);

        // Creazione e salvataggio del ruolo
        Ruolo ruolo = new Ruolo();
        ruolo.setNomeRuolo(personaleDto.getRuolo().getNomeRuolo());
        ruolo = ruoloRepository.save(ruolo);

        // Creazione del personale e impostazione delle relazioni
        Personale personale = personaleMapper.personaleDtoToPersonale(personaleDto);
        personale.setPersona(persona);
        personale.setRuolo(ruolo);

        // Salvataggio del personale
        personaleRepository.save(personale);
        return personaleMapper.personaleToPersonaleDto(personale);
    }

    public PersonaleDto findPersonaleById(final Long id) {
        try {
            return personaleMapper.personaleToPersonaleDto(personaleRepository.findById(id).get());
        } catch (Exception e) {
            throw new ElementNotFoundException("Personale non trovato");
        }
    }

    public List<PersonaleDto> findPersonaleByNomeRuolo(final String nomeRuolo) {
        try {
            List<Personale> personaleList = personaleRepository.findByRuolo_NomeRuolo(nomeRuolo);
            if (personaleList.isEmpty()) {
                throw new ElementNotFoundException("Nessun personale trovato con il ruolo " + nomeRuolo);
            }
            return personaleMapper.personaleListToPersonaleDtoList(personaleList);
        } catch (Exception e) {
            throw new ElementNotFoundException("Errore nella ricerca del personale con il ruolo " + nomeRuolo);
        }
    }

//    public List<PersonaleDto> findPersonaleByNomeRuolo(final PersonaleParams params) {
//        return personaleMapper.personaleListToPersonaleDtoList(personaleRepository.findAll(params.toSpecification()));
//    }
}
