package it.objectmethod.Biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
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
public class UtenteDto {

    private Long utenteId;

    @NotNull(message = "La data di inizio iscrizione non può essere nulla")
    @Past(message = "La data di inizio iscrizione deve essere nel passato")
    private Date inizioIscrizione;

    @Future(message = "La data di fine iscrizione deve essere nel futuro")
    private Date fineIscrizione;

    private Long personaId;
    private String nome;
    @Email(message = "L'email deve essere valida")
    private String email;
    private String telefono;
    private String password;
    private boolean isAdmin = false;
//    private List<PrenotazioneDto> prenotazioni;
//    private List<MovimentoLibroDto> movimentiLibro;
}
