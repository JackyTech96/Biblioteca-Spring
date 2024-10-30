package it.objectmethod.Biblioteca.dto;

import it.objectmethod.Biblioteca.enums.TipoIndirizzo;
import it.objectmethod.Biblioteca.enums.TipoVia;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndirizzoDto {
    @NotBlank(message = "La nazione non può essere vuota")
    private String nazione;
    @NotBlank(message = "La citta non può essere vuota")
    private String citta;
    @NotBlank(message = "La via non può essere vuota")
    @Size(min = 3, max = 50, message = "La via deve avere tra 3 e 50 caratteri")
    private String via;
    @NotBlank(message = "Il tipo via non può essere vuoto")
    private TipoVia tipoVia;
    @NotBlank(message = "Il numero civico non può essere vuoto")
    private String numeroCivico;
    @NotBlank(message = "Il cap non è vuoto")
    private String cap;
    @NotBlank(message = "Il tipo indirizzo non è vuoto")
    private TipoIndirizzo tipoIndirizzo;

//    private List<PersonaIndirizzoDto> personaIndirizzi;
}
