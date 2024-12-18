package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.IndirizzoDto;
import it.objectmethod.Biblioteca.entity.Indirizzo;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface IndirizzoMapper {
    IndirizzoDto indirizzoToIndirizzoDto(Indirizzo indirizzo);

    Indirizzo indirizzoDtoToIndirizzo(IndirizzoDto indirizzoDto);

    List<IndirizzoDto> indirizzoListToIndirizzoDtoList(List<Indirizzo> indirizzoList);

    List<Indirizzo> indirizzoDtoListToIndirizzoList(List<IndirizzoDto> indirizzoDtoList);
}
