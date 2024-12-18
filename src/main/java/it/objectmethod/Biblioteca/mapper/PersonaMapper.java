package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.security.token.AuthorizationRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PersonaMapper {
    PersonaDto personaToPersonaDto(Persona persona);

    Persona personaDtoToPersona(PersonaDto personaDto);

    List<PersonaDto> personaListToPersonaDtoList(List<Persona> personaList);

    List<Persona> personaDtoListToPersonaList(List<PersonaDto> personaDtoList);

    @Mapping(target = "id", source = "personaId")
    AuthorizationRequest toRequestForToken(Persona persona);

    @Mapping(target = "personaId", ignore = true)
    Persona updatePersona(@MappingTarget Persona persona, PersonaDto personaDto);
}
