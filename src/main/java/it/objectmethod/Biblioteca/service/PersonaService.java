package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;

    public List<PersonaDto> findAll() {
        return personaMapper.personaListToPersonaDtoList(personaRepository.findAll());
    }

    public PersonaDto createPersona(final PersonaDto personaDto) {
        return personaMapper.personaToPersonaDto(personaRepository.save(personaMapper.personaDtoToPersona(personaDto)));
    }

    protected Persona getPersonaById(final Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(String.format("Persona con id %d non trovata", id)));
    }

    protected Persona createPersona(final Persona personaDto) {
        if (personaRepository.existsById(personaDto.getPersonaId())) {
            throw new ElementNotFoundException(String.format("Persona con id %d già esistente", personaDto.getPersonaId()));
        }
        return personaRepository.save(personaDto);
    }
}
