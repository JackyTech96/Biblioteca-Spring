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

//    @GetMapping("/validate")
//    public String validateToken(@RequestParam("token") String token) {
//        boolean isValid = jwtService.validateToken(token);
//
//        if (isValid) {
//            return "Token valido";
//        } else {
//            return "Token non valido";
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody final AuthorizationRequest personaDto) {
        final ApiResponse<String> token = authService.login(personaDto);
        if (!authService.isValid(token.getData())) {
            // Se la login fallisce, restituisco un messaggio di errore
            return new ResponseEntity<>(
                    new ApiResponse<>("chi cazzo sei?"),
                    HttpStatus.UNAUTHORIZED);
        }
        token.setMessage("Login effettuato con successo");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
