package it.objectmethod.Biblioteca.IntegrationTest;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import it.objectmethod.Biblioteca.base.BaseIntegrationtest;
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
        List<PersonaDto> expected = fetchAllPersona();
        ApiResponse<PersonaDto> primaPersona = new ApiResponse<>("Persona: ", expected.get(0));


        // ACT --> AZIONE
        ApiResponse<PersonaDto> actual = given()
                .port(this.port)
                .pathParam("id", 1L)
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
                .isEqualTo(primaPersona);
    }

    @Test
    void shouldCreatePersona() {

        // ARRANGE -> ARRANGIAMENTO
        ApiResponse<PersonaDto> expected = ApiResponse.<PersonaDto>builder()
                .message("Persona:")
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

    static List<PersonaDto> fetchAllPersona() {
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
