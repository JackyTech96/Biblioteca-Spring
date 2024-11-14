package it.objectmethod.Biblioteca.security.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorizationRequest {
    private String email;
    private Long id;
    private Boolean isAdmin;
    private String password;
}