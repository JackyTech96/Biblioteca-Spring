package it.objectmethod.Biblioteca.dto;

import it.objectmethod.Biblioteca.enums.NomeRuolo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuoloDto {
    @NotNull(message = "Il nome del ruolo non può essere nullo")
    private NomeRuolo nomeRuolo;

//    private List<PersonaleDto> personale;
}
