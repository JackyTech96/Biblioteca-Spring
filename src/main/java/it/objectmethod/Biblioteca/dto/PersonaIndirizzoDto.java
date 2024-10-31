package it.objectmethod.Biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaIndirizzoDto {
    @NotNull(message = "La persona non può essere nulla")
    private Long personaId;

    @NotNull(message = "L'indirizzo non può essere nullo")
    private Long indirizzoId;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "L'email non può essere vuota")
    @Email(message = "L'email deve essere valida")
    private String email;

    @NotBlank(message = "La nazione non può essere vuota")
    private String nazione;

    @NotBlank(message = "La citta non può essere vuota")
    private String citta;
}
