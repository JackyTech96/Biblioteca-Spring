package it.objectmethod.Biblioteca.integrationTest;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import it.objectmethod.Biblioteca.base.BaseIntegrationtest;
import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.dto.MovimentoLibroDto;
import it.objectmethod.Biblioteca.dto.PrenotazioneDto;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.enums.StatoMovimento;
import it.objectmethod.Biblioteca.enums.StatoPrenotazione;
import it.objectmethod.Biblioteca.param.LibroParams;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class LibroIntegrationTest extends BaseIntegrationtest {


    @Test
    void shouldReturnLibro_whenSearchById() {
        List<LibroDto> allLibro = fetchAllLibro();
        ApiResponse<LibroDto> primoLibro = new ApiResponse<>("Libro:", allLibro.get(0));

        // ACT --> AZIONE
        ApiResponse<LibroDto> actual = given()
                .port(this.port)
                .pathParam("id", 1L)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/libro/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPeek()
                .body()
                .as(new TypeRef<>() {
                });

        // ASSERT -> ASSERZIONE
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(primoLibro);
    }

    @Test
    void shouldCreateLibro() {

        // ARRANGE -> ARRANGIAMENTO
        ApiResponse<LibroDto> expected = ApiResponse.<LibroDto>builder()
                .message("Libro creato con successo")
                .data(LibroDto.builder()
                        .libroId(3L)
                        .titolo("Harry Potter e la Camera dei Segreti")
                        .autore("J.K. Rowling")
                        .isbn("9780439061998")
                        .genere("Fantasy")
                        .editore("Bloomsbury")
                        .annoPubblicazione(Year.of(1998))
                        .copie(8)
                        .prenotazioni(List.of())
                        .movimentoLibri(List.of())
                        .build())
                .build();

        // ACT -> AZIONE
        ApiResponse<LibroDto> actual = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(expected.getData())
                .when()
                .post("/api/libro")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .body()
                .as(new TypeRef<>() {
                });

        // ASSERT -> ASSERZIONE
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void shouldUpdateLibro() {
        List<LibroDto> allLibro = fetchAllLibro();
        LibroDto libroDtoToUpdate = allLibro.get(0);
        libroDtoToUpdate.setCopie(10);

        LibroDto expected = LibroDto.builder()
                .libroId(1L)
                .titolo("Il Signore degli Anelli")
                .autore("J.R.R. Tolkien")
                .isbn("9788804651954")
                .genere("Fantasy")
                .editore("Mondadori")
                .annoPubblicazione(Year.of(1954))
                .copie(10)
                .prenotazioni(List.of(
                        PrenotazioneDto.builder()
                                .libroId(1L)
                                .utenteId(1L)
                                .dataPrenotazione(new Date(122, 0, 1)) // 2022-01-01
                                .stato(StatoPrenotazione.ATTIVA)
                                .build()
                ))
                .movimentoLibri(List.of(
                        MovimentoLibroDto.builder()
                                .libroId(1L)
                                .utenteId(1L)
                                .dataPrestito(new Date(122, 0, 1))
                                .dataScadenzaRestituzione(new Date(122, 1, 1)) // 2022-02-01
                                .dataRestituzione(null)
                                .stato(StatoMovimento.ATTIVO)
                                .build()
                ))
                .build();

        // ACT --> AZIONE
        LibroDto actual = given()
                .port(this.port)
                .pathParam("id", 1L)
                .contentType(ContentType.JSON)
                .body(libroDtoToUpdate)
                .put("/api/libro/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPeek()
                .body()
                .as(new TypeRef<>() {
                });

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);

    }

    @Test
    void shouldReturnLibro_whenSearchByTitolo() {
        LibroParams libroParams = new LibroParams();
        libroParams.setTitolo("Il Signore degli Anelli");

        // ARRANGE -> ARRANGIAMENTO
        List<LibroDto> expected = List.of(
                LibroDto.builder()
                        .libroId(1L)
                        .titolo("Il Signore degli Anelli")
                        .autore("J.R.R. Tolkien")
                        .isbn("9788804651954")
                        .genere("Fantasy")
                        .editore("Mondadori")
                        .annoPubblicazione(Year.of(1954))
                        .copie(5)
                        .prenotazioni(List.of(
                                PrenotazioneDto.builder()
                                        .libroId(1L)
                                        .utenteId(1L)
                                        .dataPrenotazione(new Date(122, 0, 1)) // 2022-01-01
                                        .stato(StatoPrenotazione.ATTIVA)
                                        .build()
                        ))
                        .movimentoLibri(List.of(
                                MovimentoLibroDto.builder()
                                        .libroId(1L)
                                        .utenteId(1L)
                                        .dataPrestito(new Date(122, 0, 1))
                                        .dataScadenzaRestituzione(new Date(122, 1, 1)) // 2022-02-01
                                        .dataRestituzione(null)
                                        .stato(StatoMovimento.ATTIVO)
                                        .build()
                        ))
                        .build()
        );

        // ACT --> AZIONE
        List<LibroDto> actual = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .queryParam("titolo", libroParams.getTitolo())
                .when()
                .get("/api/libro/spec")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPeek()
                .body()
                .as(new TypeRef<>() {
                });


        // ASSERT -> ASSERZIONE
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    static List<LibroDto> fetchAllLibro() {
        return List.of(
                LibroDto.builder()
                        .libroId(1L)
                        .titolo("Il Signore degli Anelli")
                        .autore("J.R.R. Tolkien")
                        .isbn("9788804651954")
                        .genere("Fantasy")
                        .editore("Mondadori")
                        .annoPubblicazione(Year.of(1954))
                        .copie(5)
                        .prenotazioni(List.of(
                                PrenotazioneDto.builder()
                                        .libroId(1L)
                                        .utenteId(1L)
                                        .dataPrenotazione(new Date(122, 0, 1)) // 2022-01-01
                                        .stato(StatoPrenotazione.ATTIVA)
                                        .build()
                        ))
                        .movimentoLibri(List.of(
                                MovimentoLibroDto.builder()
                                        .libroId(1L)
                                        .utenteId(1L)
                                        .dataPrestito(new Date(122, 0, 1))
                                        .dataScadenzaRestituzione(new Date(122, 1, 1)) // 2022-02-01
                                        .dataRestituzione(null)
                                        .stato(StatoMovimento.ATTIVO)
                                        .build()
                        ))

                        .build(),
                LibroDto.builder()
                        .libroId(2L)
                        .titolo("Harry Potter e la Pietra Filosofale")
                        .autore("J.K. Rowling")
                        .isbn("9788804652787")
                        .genere("Fantasy")
                        .editore("Mondadori")
                        .annoPubblicazione(Year.of(1997))
                        .copie(5)
                        .prenotazioni(List.of(
                                PrenotazioneDto.builder()
                                        .libroId(2L)
                                        .utenteId(2L)
                                        .dataPrenotazione(new Date(122, 0, 1))
                                        .stato(StatoPrenotazione.COMPLETATA)
                                        .build()
                        ))
                        .movimentoLibri(List.of(
                                MovimentoLibroDto.builder()
                                        .libroId(2L)
                                        .utenteId(2L)
                                        .dataPrestito(new Date(122, 0, 1))
                                        .dataScadenzaRestituzione(new Date(122, 1, 1))
                                        .dataRestituzione(new Date(122, 1, 1))
                                        .stato(StatoMovimento.RESTITUITO)
                                        .build()
                        ))
                        .build()
        );
    }
}
