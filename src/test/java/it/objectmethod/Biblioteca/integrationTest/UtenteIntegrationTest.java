package it.objectmethod.Biblioteca.integrationTest;

import io.restassured.common.mapper.TypeRef;
import it.objectmethod.Biblioteca.base.BaseIntegrationtest;
import it.objectmethod.Biblioteca.dto.UtenteDto;
import it.objectmethod.Biblioteca.param.UtenteParams;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UtenteIntegrationTest extends BaseIntegrationtest {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Date getDataFineIscrizione() {
        return Date.from(
                LocalDate.now()
                        .plusYears(1)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
        );
    }

    private UtenteDto createUtenteDto() {
        return UtenteDto.builder()
                .personaId(6L)
                .nome("mario")
                .email("mario@rossi.it")
                .telefono("123456789")
                .password("password")
                .inizioIscrizione(new Date())
                .fineIscrizione(getDataFineIscrizione())
                .build();

    }

    @Test
    public void shouldCreateUtenteWithPersona() {
        // ARRANGE: Prepara i dati di test e le aspettative
        final ApiResponse<UtenteDto> expected = new ApiResponse<>("Operazione riuscita", createUtenteDto());

        //ACT: Esegui il metodo da testare
        ApiResponse<UtenteDto> actual = given()
                .port(this.port)
                .contentType("application/json")
                .body(createUtenteDto())
                .when()
                .post("/api/utente")
                .then()
                .statusCode(201)
                .extract()
                .response()
                .body()
                .as(new TypeRef<>() {
                });

        // ASSERT: Verifica che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("data.password", "data.inizioIscrizione")
                .isEqualTo(expected);
    }

    @Test
    public void shouldReturnUtente_whenSearchById() {
        //ARRANGE: Prepara i dati di test e le aspettative
        UtenteParams utenteParams = new UtenteParams();
        utenteParams.setUtenteId(1L);

        final List<UtenteDto> expected = List.of(
                fetchAllUtenti().get(0)
        );

        //ACT: Esegui il metodo da testare
        final List<UtenteDto> actual = given()
                .port(this.port)
                .queryParam("utenteId", 1L)
                .contentType("application/json")
                .when()
                .get("/api/utente/spec")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .as(new TypeRef<>() {
                });

        //ASSERT: Verifica che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("password", "isAdmin", "inizioIscrizione", "fineIscrizione")
                .isEqualTo(expected);
    }

    private static List<UtenteDto> fetchAllUtenti() {
        return List.of(
                UtenteDto.builder()
                        .personaId(1L)
                        .nome("gigi")
                        .email("gi@fra")
                        .telefono("324888974")
                        .password("12345")
                        .isAdmin(true)
                        .inizioIscrizione(new Date())
                        .fineIscrizione(Date.from(LocalDate.now().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build(),

                UtenteDto.builder()
                        .personaId(2L)
                        .nome("gianni")
                        .email("ga@fra")
                        .telefono("324888975")
                        .password("password")
                        .isAdmin(false)
                        .inizioIscrizione(new Date())
                        .fineIscrizione(Date.from(LocalDate.now().plusYears(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                        .build()
        );
    }
}
