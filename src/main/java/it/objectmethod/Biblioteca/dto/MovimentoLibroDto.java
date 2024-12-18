package it.objectmethod.Biblioteca.dto;

import it.objectmethod.Biblioteca.enums.StatoMovimento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private Date dataScadenzaRestituzione;

    private Date dataRestituzione;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Lo stato non può essere nullo")
    private StatoMovimento stato;

    @NotNull(message = "Il libro non può essere nullo")
    private Long libroId;

    @NotNull(message = "L'utente non può essere nullo")
    private Long utenteId;
}
