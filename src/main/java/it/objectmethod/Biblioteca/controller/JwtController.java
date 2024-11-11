package it.objectmethod.Biblioteca.controller;

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

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        boolean isValid = jwtService.validateToken(token);

        if (isValid) {
            return "Token valido";
        } else {
            return "Token non valido";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody final LoginDto loginDto) {
        String token = jwtService.authenticateAndGenerateToken(loginDto.getEmail(), loginDto.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
