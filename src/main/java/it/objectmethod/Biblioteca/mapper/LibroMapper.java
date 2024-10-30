package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.entity.Libro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibroMapper {
    LibroDto libroToLibroDto(Libro libro);

    Libro libroDtoToLibro(LibroDto libroDto);

    List<LibroDto> libriToLibroDto(List<Libro> libri);

    List<Libro> libriDtoToLibri(List<LibroDto> libriDto);
}
