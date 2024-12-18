package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.PrenotazioneDto;
import it.objectmethod.Biblioteca.entity.Prenotazione;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {LibroMapper.class, UtenteMapper.class})
public interface PrenotazioneMapper {

    @Mapping(target = "libroId", source = "libro.libroId")
    @Mapping(target = "utenteId", source = "utente.utenteId")
    PrenotazioneDto prenotazioneToPrenotazioneDto(Prenotazione prenotazione);

    @Mapping(target = "libro.libroId", source = "libroId")
    @Mapping(target = "utente.utenteId", source = "utenteId")
    Prenotazione prenotazioneDtoToPrenotazione(PrenotazioneDto prenotazioneDto);

    List<PrenotazioneDto> prenotazioneListToPrenotazioneDtoList(List<Prenotazione> prenotazioneList);

    List<Prenotazione> prenotazioneDtoListToPrenotazioneList(List<PrenotazioneDto> prenotazioneDtoList);
}
