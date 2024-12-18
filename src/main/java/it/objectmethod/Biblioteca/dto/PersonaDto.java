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
    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @Email(message = "L'email deve essere valida")
    @NotBlank(message = "L'email non può essere vuota")
    private String email;
    @NotBlank(message = "Il telefono non può essere vuoto")
    private String telefono;
    @NotBlank(message = "La password non può essere vuota")
    private String password;

    private boolean isAdmin = false;

//    private List<UtenteDto> utenti;
//    private List<PersonaleDto> personale;
//    private List<PersonaIndirizzoDto> indirizzi;

}
