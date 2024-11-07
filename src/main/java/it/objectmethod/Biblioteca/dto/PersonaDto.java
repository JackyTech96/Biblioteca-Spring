package it.objectmethod.Biblioteca.dto;

import it.objectmethod.Biblioteca.validation.RegexValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaDto {

    private Long personaId;
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
