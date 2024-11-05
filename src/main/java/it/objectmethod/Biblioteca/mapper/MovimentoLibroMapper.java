package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.MovimentoLibroDto;
import it.objectmethod.Biblioteca.entity.MovimentoLibro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LibroMapper.class, UtenteMapper.class})
public interface MovimentoLibroMapper {

    @Mapping(target = "libroId", source = "libro.libroId")
    @Mapping(target = "utenteId", source = "utente.utenteId")
    MovimentoLibroDto movimentoLibroToMovimentoLibroDto(MovimentoLibro movimentoLibro);

    @Mapping(target = "libro.libroId", source = "libroId")
    @Mapping(target = "utente.utenteId", source = "utenteId")
    MovimentoLibro movimentoLibroDtoToMovimentoLibro(MovimentoLibroDto movimentoLibroDto);

    List<MovimentoLibroDto> movimentoLibroListToMovimentoLibroDtoList(List<MovimentoLibro> movimentoLibroList);

    List<MovimentoLibro> movimentoLibroDtoListToMovimentoLibroList(List<MovimentoLibroDto> movimentoLibroDtoList);
}
