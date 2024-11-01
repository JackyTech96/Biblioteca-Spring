package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.UtenteDto;
import it.objectmethod.Biblioteca.entity.Utente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UtenteMapper {

    @Mapping(target = "personaId", source = "persona.personaId")
    @Mapping(target = "nome", source = "persona.nome")
    @Mapping(target = "email", source = "persona.email")
    @Mapping(target = "telefono", source = "persona.telefono")
    UtenteDto utenteToUtenteDto(Utente utente);

    //    @Mapping(target = "personaId", source = "persona.personaId")
//    @Mapping(target = "nome", source = "persona.nome")
//    @Mapping(target = "email", source = "persona.email")
//    @Mapping(target = "telefono", source = "persona.telefono")
    Utente utenteDtoToUtente(UtenteDto utenteDto);

//    @Mapping(target = "inizioIscrizione", source = "inizioIscrizione", qualifiedByName = "inizioIscrizione")
//    @Mapping(target = "fineIscrizione", source = "fineIscrizione", qualifiedByName = "fineIscrizione")
//    Utente toCreateUtente(UtenteDto utenteDto);

    List<UtenteDto> utenteListToUtenteDtoList(List<Utente> utenteList);

    List<Utente> utenteDtoListToUtenteList(List<UtenteDto> utenteDtoList);

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

