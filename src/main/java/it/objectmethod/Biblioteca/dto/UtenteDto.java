package it.objectmethod.Biblioteca.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtenteDto {

    @NotNull(message = "La data di inizio iscrizione non può essere nulla")
    @Past(message = "La data di inizio iscrizione deve essere nel passato")
    private Date inizioIscrizione;

    @Future(message = "La data di fine iscrizione deve essere nel futuro")
    private Date fineIscrizione;

    private Long personaId;
    private String nome;
    private String email;
    private String telefono;
    private List<PrenotazioneDto> prenotazioni;
    private List<MovimentoLibroDto> movimentiLibro;
}
