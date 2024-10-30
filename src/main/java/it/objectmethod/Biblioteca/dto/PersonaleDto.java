package it.objectmethod.Biblioteca.dto;

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

    private Long personaId;
    private String nome;
    private String email;
    private String telefono;

    private RuoloDto ruolo;
}
