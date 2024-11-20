package it.objectmethod.Biblioteca.integrationTest;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import it.objectmethod.Biblioteca.base.BaseIntegrationtest;
import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class PersonaIntegrationTest extends BaseIntegrationtest {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Test
    void shouldReturnPersona_whenSearchById() {
        final List<PersonaDto> expected = fetchAllPersona();
        final ApiResponse<PersonaDto> secondaPersona = new ApiResponse<>("Persona:", expected.get(1));


        // ACT --> AZIONE
        ApiResponse<PersonaDto> actual = given()
                .port(this.port)
                .pathParam("id", 2L)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/persona/{id}")
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
                .isEqualTo(secondaPersona);
    }

    @Test
    void shouldCreatePersona() {

        // ARRANGE -> ARRANGIAMENTO
        ApiResponse<PersonaDto> expected = ApiResponse.<PersonaDto>builder()
                .message("Persona creata con successo:")
                .data(PersonaDto.builder()
                        .personaId(6L)
                        .nome("giuseppe")
                        .email("giuseppe@fra")
                        .telefono("324888976")
                        .password(passwordEncoder.encode("12345"))
                        .isAdmin(false)
                        .build())
                .build();

        // ACT -> AZIONE
        ApiResponse<PersonaDto> actual = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(expected.getData())
                .when()
                .post("/api/persona")
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
                .ignoringFields("data.password")
                .isEqualTo(expected);
    }

    @Test
    void shouldUpdatePersona() {
        List<PersonaDto> allPersona = fetchAllPersona();
        PersonaDto personaToUpdate = allPersona.get(0);
        personaToUpdate.setNome("carlo");

        ApiResponse<PersonaDto> expected = ApiResponse.<PersonaDto>builder()
                .message("Persona aggiornata con successo")
                .data(PersonaDto.builder()
                        .personaId(1L)
                        .nome("carlo")
                        .email("gi@fra")
                        .telefono("324888974")
                        .password("12345")
                        .isAdmin(true)
                        .build())
                .build();

        ApiResponse<PersonaDto> actual = given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(personaToUpdate)
                .pathParam("id", 1L)
                .when()
                .put("/api/persona/{id}")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .body()
                .as(new TypeRef<>() {
                });

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("data.password")
                .isEqualTo(expected);

    }

    private static List<PersonaDto> fetchAllPersona() {
        return List.of(PersonaDto.builder()
                        .personaId(1L)
                        .nome("gigi")
                        .email("gi@fra")
                        .telefono("324888974")
                        .password("12345")
                        .isAdmin(true)
                        .build(),
                PersonaDto.builder()
                        .personaId(2L)
                        .nome("gianni")
                        .email("ga@fra")
                        .telefono("324888975")
                        .password("12345")
                        .isAdmin(false)
                        .build(),
                PersonaDto.builder()
                        .personaId(3L)
                        .nome("paolo")
                        .email("paolo@fra")
                        .telefono("324888973")
                        .password("12345")
                        .isAdmin(false)
                        .build(),
                PersonaDto.builder()
                        .personaId(4L)
                        .nome("luca")
                        .email("luca@fra")
                        .telefono("324888972")
                        .password("12345")
                        .isAdmin(false)
                        .build(),
                PersonaDto.builder()
                        .personaId(5L)
                        .nome("marco")
                        .email("marco@fra")
                        .telefono("324888971")
                        .password("12345")
                        .isAdmin(false)
                        .build());

    }
}
