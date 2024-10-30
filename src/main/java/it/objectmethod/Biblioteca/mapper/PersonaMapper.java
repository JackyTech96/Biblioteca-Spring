package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaDto personaToPersonaDto(Persona persona);

    Persona personaDtoToPersona(PersonaDto personaDto);

    List<PersonaDto> personaListToPersonaDtoList(List<Persona> personaList);

    List<Persona> personaDtoListToPersonaList(List<PersonaDto> personaDtoList);
}
