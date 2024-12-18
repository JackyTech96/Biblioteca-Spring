package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.security.token.AuthorizationRequest;
import it.objectmethod.Biblioteca.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    @Autowired
    private AuthService authService;

    @GetMapping("/generate")
    public String generateToken(@RequestBody final AuthorizationRequest request) {
        return authService.createToken(request);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody final AuthorizationRequest personaDto) {
        // Chiama il metodo di login dell'AuthService per ottenere un token JWT se le credenziali sono corrette
        final ApiResponse<String> token = authService.login(personaDto);

        // Verifica se il token ottenuto è valido
        if (!authService.isValid(token.getData())) {
            // Se la login fallisce, restituisce un messaggio di errore con stato HTTP 401
            return new ResponseEntity<>(
                    new ApiResponse<>("Errore di autenticazione: credenziali non valide"),
                    HttpStatus.UNAUTHORIZED);
        }


        // Restituisce il token e un messaggio di successo con stato HTTP 200
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
