package it.objectmethod.Biblioteca.unit;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.mapper.LibroMapper;
import it.objectmethod.Biblioteca.param.LibroParams;
import it.objectmethod.Biblioteca.repository.LibroRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.LibroService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@MockitoSettings
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private LibroMapper libroMapper;

    @Mock
    private LibroParams libroParams;

    @InjectMocks
    private LibroService libroService;

    static final LibroDto libroDto = LibroDto.builder()
            .libroId(1L)
            .titolo("titolo")
            .autore("autore")
            .isbn("isbn")
            .genere("genere")
            .editore("editore")
            .annoPubblicazione(Year.of(2022))
            .copie(1)
            .build();

    static final Libro libroEntity = Libro.builder()
            .libroId(1L)
            .titolo("titolo")
            .autore("autore")
            .isbn("isbn")
            .genere("genere")
            .editore("editore")
            .annoPubblicazione(Year.of(2022))
            .copie(1)
            .build();

    @Test
    void findAll() {

        // ARRANGE: Prepara i dati di test e le aspettative
        final ApiResponse<List<LibroDto>> expected = new ApiResponse<>();
        expected.setMessage("Libri:");
        final List<LibroDto> libroDtoList = List.of(libroDto);
        expected.setData(libroDtoList);
        final List<Libro> libroList = List.of(libroEntity);

        // Configura i mock per restituire i dati preparati
        when(libroRepository.findAll()).thenReturn(libroList);
        when(libroMapper.libriToLibroDto(libroList)).thenReturn(libroDtoList);

        // ACT: Eseguo il metodo da testare
        final ApiResponse<List<LibroDto>> actual = libroService.getAllLibri();

        // ASSERT: Verifico che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void findById() {
        final ApiResponse<LibroDto> expected = new ApiResponse<>();
        expected.setMessage("Libro:");
        expected.setData(libroDto);

        when(libroRepository.findById(1L)).thenReturn(Optional.of(libroEntity));
        when(libroMapper.libroToLibroDto(libroEntity)).thenReturn(libroDto);

        final ApiResponse<LibroDto> actual = libroService.getLibroById(1L);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void shouldCreateLibro() {
        final ApiResponse<LibroDto> expected = new ApiResponse<>();
        expected.setMessage("Libro creato con successo");
        expected.setData(libroDto);

        final LibroDto libroDtoToSave = LibroDto.builder()
                .libroId(null)
                .titolo("titolo")
                .autore("autore")
                .isbn("isbn")
                .genere("genere")
                .editore("editore")
                .annoPubblicazione(Year.of(2024))
                .copie(5)
                .build();

        when(libroMapper.libroDtoToLibro(libroDtoToSave)).thenReturn(libroEntity);
        when(libroRepository.save(libroEntity)).thenReturn(libroEntity);
        when(libroMapper.libroToLibroDto(libroEntity)).thenReturn(libroDto);

        final ApiResponse<LibroDto> actual = libroService.createLibro(libroDtoToSave);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void shouldReturnLibro_whenSearchBySpecification() {
        final List<Libro> libroList = List.of(Libro.builder()
                        .libroId(1L)
                        .titolo("titolo 1")
                        .autore("autore 1")
                        .isbn("isbn 1")
                        .genere("genere 1")
                        .editore("editore 1")
                        .annoPubblicazione(Year.of(2022))
                        .copie(1)
                        .build(),
                Libro.builder()
                        .libroId(2L)
                        .titolo("titolo 2")
                        .autore("autore 2")
                        .isbn("isbn 2")
                        .genere("genere 2")
                        .editore("editore 2")
                        .annoPubblicazione(Year.of(2023))
                        .copie(2)
                        .build());


        libroParams.setTitolo("titolo 1");

     /*   when(libroRepository.findAll(libroParams.toSpecification())).thenReturn(libroList.stream() // Errore qui.
                .filter(libro -> libro.getTitolo().equals(libroParams.getTitolo()))
                .toList());*/

/*        doReturn(libroList.stream()
                .filter(libro -> libro.getTitolo().equals(libroParams.getTitolo()))
                .toList());
        when(libroRepository.findAll(libroParams.toSpecification()));*/

        List<Libro> filteredList = libroList.stream()
                .filter(libro -> libro.getTitolo().equals(libroParams.getTitolo()))
                .toList();

        when(libroRepository.findAll(libroParams.toSpecification())).thenReturn(filteredList);

        final List<LibroDto> libroDtoList = List.of(
                LibroDto.builder()
                        .libroId(1L)
                        .titolo("titolo 1")
                        .autore("autore 1")
                        .isbn("isbn 1")
                        .genere("genere 1")
                        .editore("editore 1")
                        .annoPubblicazione(Year.of(2022))
                        .copie(1)
                        .build()
        );
        when(libroMapper.libriToLibroDto(libroList)).thenReturn(libroDtoList);

        final List<LibroDto> actual = libroService.findWithSpecification(libroParams);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(libroDtoList);
    }
}
