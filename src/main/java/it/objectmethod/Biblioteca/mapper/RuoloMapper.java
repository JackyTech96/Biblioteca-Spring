package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.RuoloDto;
import it.objectmethod.Biblioteca.entity.Ruolo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RuoloMapper {
    RuoloDto ruoloToRuoloDto(Ruolo ruolo);

    Ruolo ruoloDtoToRuolo(RuoloDto ruoloDto);

    List<RuoloDto> ruoloListToRuoloDtoList(List<Ruolo> ruoloList);

    List<Ruolo> ruoloDtoListToRuoloList(List<RuoloDto> ruoloDtoList);


}
