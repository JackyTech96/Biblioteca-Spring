package it.objectmethod.Biblioteca.dto;

import it.objectmethod.Biblioteca.validation.IsbnAnnoValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IsbnAnnoValidation
public class LibroDto {

    @NotBlank(message = "Il titolo non può essere vuoto")
    private String titolo;

    @NotBlank(message = "L'autore non può essere vuoto")
    private String autore;

    @NotBlank(message = "L'ISBN non può essere vuoto")
    private String isbn;

    @NotBlank(message = "Il genere non può essere vuoto")
    private String genere;

    @NotBlank(message = "L'editore non può essere vuoto")
    private String editore;

    @NotNull(message = "L'anno di pubblicazione non può essere nullo")
    private Year annoPubblicazione;

    @NotNull(message = "Il numero di copie non può essere nullo")
    @Positive(message = "Il numero di copie deve essere un numero positivo")
    private Integer copie;

//    private List<PrenotazioneDto> prenotazioni;
//    private List<MovimentoLibroDto> movimentoLibri;
}
