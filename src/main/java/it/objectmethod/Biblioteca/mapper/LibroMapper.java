package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.dto.LibroExportDto;
import it.objectmethod.Biblioteca.entity.Libro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PrenotazioneMapper.class, MovimentoLibroMapper.class})
public interface LibroMapper {
    @Mapping(target = "movimentoLibri", source = "movimentiLibro")
    LibroDto libroToLibroDto(Libro libro);

    @Mapping(target = "movimentiLibro", source = "movimentoLibri")
    Libro libroDtoToLibro(LibroDto libroDto);

    List<LibroDto> libriToLibroDto(List<Libro> libri);

    List<Libro> libriDtoToLibri(List<LibroDto> libriDto);

    @Mapping(target = "libroId", ignore = true)
    public void updateLibro(@MappingTarget Libro libro, LibroDto libroDto);


    // Mappatura per LibroExportDto, senza relazioni
    LibroExportDto libroToLibroExportDto(Libro libro);

    Libro libroExportDtoToLibro(LibroExportDto libroExportDto);

    List<LibroExportDto> libriToLibroExportDtoList(List<Libro> libri);
}
