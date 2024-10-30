package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.PersonaleDto;
import it.objectmethod.Biblioteca.entity.Personale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RuoloMapper.class, PersonaMapper.class})
public interface PersonaleMapper {
    @Mapping(target = "personaId", source = "persona.personaId")
    @Mapping(target = "nome", source = "persona.nome")
    @Mapping(target = "email", source = "persona.email")
    @Mapping(target = "telefono", source = "persona.telefono")
//    @Mapping(target = "ruolo", source = "ruolo")
    PersonaleDto personaleToPersonaleDto(Personale personale);

    @Mapping(target = "persona.personaId", source = "personaId")
    @Mapping(target = "persona.nome", source = "nome")
    @Mapping(target = "persona.email", source = "email")
    @Mapping(target = "persona.telefono", source = "telefono")
//    @Mapping(target = "ruolo", source = "ruolo")
    Personale personaleDtoToPersonale(PersonaleDto personaleDto);

    List<PersonaleDto> personaleListToPersonaleDtoList(List<Personale> personaleList);

    List<Personale> personaleDtoListToPersonaleList(List<PersonaleDto> personaleDtoList);


}
