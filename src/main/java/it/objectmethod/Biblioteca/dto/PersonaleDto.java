package it.objectmethod.Biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaleDto {

    @NotNull(message = "La data di assunzione non può essere nulla")
    @Past(message = "La data di assunzione deve essere nel passato")
    private Date dataAssunzione;

    @NotNull(message = "La persona non può essere nulla")
    private Long personaId;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "L'email non può essere vuota")
    @Email(message = "L'email deve essere valida")
    private String email;

    @NotBlank(message = "Il telefono non può essere vuoto")
    private String telefono;

    private String password;

    @NotNull(message = "Il ruolo non può essere nullo")
    private RuoloDto ruolo;
}
