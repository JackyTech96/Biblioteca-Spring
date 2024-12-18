package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.UtenteDto;
import it.objectmethod.Biblioteca.entity.Utente;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UtenteMapper {

    @Mapping(target = "personaId", source = "persona.personaId")
    @Mapping(target = "nome", source = "persona.nome")
    @Mapping(target = "email", source = "persona.email")
    @Mapping(target = "telefono", source = "persona.telefono")
    @Mapping(target = "password", source = "persona.password")
    UtenteDto utenteToUtenteDto(Utente utente);

    @Mapping(target = "persona.personaId", source = "personaId")
    @Mapping(target = "persona.nome", source = "nome")
    @Mapping(target = "persona.email", source = "email")
    @Mapping(target = "persona.telefono", source = "telefono")
    @Mapping(target = "persona.password", source = "password")
    Utente utenteDtoToUtente(UtenteDto utenteDto);

    @Mapping(target = "personaId", source = "persona.personaId")
    @Mapping(target = "nome", source = "persona.nome")
    @Mapping(target = "email", source = "persona.email")
    @Mapping(target = "telefono", source = "persona.telefono")
    @Mapping(target = "password", source = "persona.password")
    List<UtenteDto> utenteListToUtenteDtoList(List<Utente> utenteList);

    List<Utente> utenteDtoListToUtenteList(List<UtenteDto> utenteDtoList);

    default List<UtenteDto> toDtoPage(Page<Utente> utentePage) {
        return utentePage
                .stream()
                .map(this::utenteToUtenteDto)
                .toList();
    }


//    @Named("inizioIscrizione")
//    default Date setInizioIscrizione(Date inizioIscrizione) {
//        return new Date();
//    }
//    @Named("fineIscrizione")
//    default Date setFineIscrizione(Date fineIscrizione) {
//        LocalDate fineIscrizioneLocalDate = LocalDate.now().plusYears(1);
//        // Converto la LocalDate in Date utilizzando la zona di default del sistema
//        // e impostando l'ora all'inizio del giorno
//        fineIscrizione = Date.from(fineIscrizioneLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        return fineIscrizione;
}

