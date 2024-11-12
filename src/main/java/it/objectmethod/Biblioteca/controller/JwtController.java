package it.objectmethod.Biblioteca.controller;

import io.micrometer.common.util.StringUtils;
import it.objectmethod.Biblioteca.dto.LoginDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @GetMapping("/generate")
    public String generateToken(@RequestParam("email") String email, @RequestParam("id") Long id) {

        Persona persona = new Persona();
        persona.setEmail(email);
        persona.setPersonaId(id);

        return jwtService.generateToken(persona);
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
    public ResponseEntity<ApiResponse<String>> login(@RequestBody final LoginDto loginDto) {
        try {
            // Verifico se email e password sono state fornite
            // if (loginDto.getEmail() == null || loginDto.getEmail().isEmpty())
            if (StringUtils.isBlank(loginDto.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>("Email non fornita"));
            }
//            if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty())
            if (StringUtils.isBlank(loginDto.getPassword())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>("Password non fornita"));
            }

            // Faccio la login con email e password
            String token = jwtService.authenticateAndGenerateToken(loginDto.getEmail(), loginDto.getPassword());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>("Login riuscito", token));
        } catch (IllegalArgumentException e) {
            // Se la login fallisce, restituisco un messaggio di errore
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("Login fallito"));
        } catch (Exception e) {
            // Se si verifica un errore interno del server, restituisco un messaggio di errore generico
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Errore interno del server"));
        }
    }
}
