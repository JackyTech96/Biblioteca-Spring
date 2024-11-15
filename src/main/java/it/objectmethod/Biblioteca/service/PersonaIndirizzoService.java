package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.PersonaIndirizzoDto;
import it.objectmethod.Biblioteca.entity.PersonaIndirizzo;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.mapper.PersonaIndirizzoMapper;
import it.objectmethod.Biblioteca.repository.PersonaIndirizzoRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaIndirizzoService {
    @Autowired
    private PersonaIndirizzoRepository personaIndirizzoRepository;
    @Autowired
    private PersonaIndirizzoMapper personaIndirizzoMapper;

    public List<PersonaIndirizzoDto> getAll() {
        return personaIndirizzoMapper.personaIndirizzoListToPersonaIndirizzoDtoList(personaIndirizzoRepository.findAll());
    }

    public ApiResponse<List<PersonaIndirizzoDto>> getPersonaWithIndirizzo(Long personaId) {
        List<PersonaIndirizzo> personaIndirizzoList = personaIndirizzoRepository.findAllPersonaWithIndirizzo(personaId);
        if (personaIndirizzoList == null || personaIndirizzoList.isEmpty()) {
            throw new ElementNotFoundException("Nessuna persona con id " + personaId);
        }
        List<PersonaIndirizzoDto> personaIndirizzoDtoListToFound = personaIndirizzoMapper
                .personaIndirizzoListToPersonaIndirizzoDtoList(personaIndirizzoList);
        return new ApiResponse<>("Indirizzi trovati", personaIndirizzoDtoListToFound);
    }
}

