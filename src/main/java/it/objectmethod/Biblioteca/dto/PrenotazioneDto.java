package it.objectmethod.Biblioteca.dto;

import it.objectmethod.Biblioteca.enums.StatoPrenotazione;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrenotazioneDto {

    @NotNull(message = "La data di prenotazione non può essere nulla")
    @Past(message = "La data di prenotazione deve essere nel passato")
    private Date dataPrenotazione;

    @NotNull(message = "Lo stato non può essere nullo")
    private StatoPrenotazione stato;

    private LibroDto libro;
    private UtenteDto utente;
}
