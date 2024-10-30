package it.objectmethod.Biblioteca.dto;

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
    private Date dataPrestito;
    private Date dataScadenzaRestituzione;
    private Date dataRestituzione;
    private String stato;
    private LibroDto libro;
    private UtenteDto utente;
}
