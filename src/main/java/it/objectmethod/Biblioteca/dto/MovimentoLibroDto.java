package it.objectmethod.Biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimentoLibroDto {
    @NotNull(message = "La data di prestito non può essere nulla")
    private Date dataPrestito;

    @NotNull(message = "La data di scadenza restituzione non può essere nulla")
    private Date dataScadenzaRestituzione;

    private Date dataRestituzione;

    @NotBlank(message = "Lo stato non può essere vuoto")
    private String stato;

    @NotNull(message = "Il libro non può essere nullo")
    private Long libroId;

    @NotNull(message = "L'utente non può essere nullo")
    private Long utenteId;
}
