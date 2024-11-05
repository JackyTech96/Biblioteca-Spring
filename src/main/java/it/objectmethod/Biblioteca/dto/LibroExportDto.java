package it.objectmethod.Biblioteca.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroExportDto {
    private Long libroId;

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
    @Min(value = 1, message = "Il numero di copie non è valido")
    private Integer copie;
}
