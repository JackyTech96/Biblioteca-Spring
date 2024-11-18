package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.PersonaIndirizzoDto;
import it.objectmethod.Biblioteca.entity.PersonaIndirizzo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PersonaIndirizzoMapper {
    @Mapping(target = "personaId", source = "persona.personaId")
    @Mapping(target = "indirizzoId", source = "indirizzo.indirizzoId")
    @Mapping(target = "nome", source = "persona.nome")
    @Mapping(target = "email", source = "persona.email")
    @Mapping(target = "nazione", source = "indirizzo.nazione")
    @Mapping(target = "citta", source = "indirizzo.citta")
    PersonaIndirizzoDto personaIndirizzoToPersonaIndirizzoDto(PersonaIndirizzo personaIndirizzo);

    @Mapping(target = "persona.personaId", source = "personaId")
    @Mapping(target = "indirizzo.indirizzoId", source = "indirizzoId")
    @Mapping(target = "persona.nome", source = "nome")
    @Mapping(target = "persona.email", source = "email")
    @Mapping(target = "indirizzo.nazione", source = "nazione")
    @Mapping(target = "indirizzo.citta", source = "citta")
    PersonaIndirizzo personaIndirizzoDtoToPersonaIndirizzo(PersonaIndirizzoDto personaIndirizzoDto);

    List<PersonaIndirizzoDto> personaIndirizzoListToPersonaIndirizzoDtoList(List<PersonaIndirizzo> personaIndirizzoList);

    List<PersonaIndirizzo> personaIndirizzoDtoListToPersonaIndirizzoList(List<PersonaIndirizzoDto> personaIndirizzoDtoList);
}
