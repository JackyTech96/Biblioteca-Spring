package it.objectmethod.Biblioteca.dto;

import it.objectmethod.Biblioteca.validation.RegexValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaDto {

    //    @NotBlank(message = "Il nome non può essere vuoto")
    @RegexValidation(regexp = "^[a-zA-Z]+$", message = "Il nome deve contenere solo lettere", nullable = false)
    private String nome;

    @Email(message = "L'email deve essere valida")
    @NotBlank(message = "L'email non può essere vuota")
    private String email;
    private String telefono;

//    private List<UtenteDto> utenti;
//    private List<PersonaleDto> personale;
//    private List<PersonaIndirizzoDto> indirizzi;

}
