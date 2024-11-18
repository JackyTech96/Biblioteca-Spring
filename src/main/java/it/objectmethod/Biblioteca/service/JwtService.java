package it.objectmethod.Biblioteca.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import it.objectmethod.Biblioteca.constant.Constants;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JwtService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${password.jwt}") // modo più sicuro
    private String secret;


    /**
     * Genera un token JWT con scadenza di 1 giorno e chiave di firma
     * definita nella costante Constants.JWT_SECRET.
     *
     * @return il token JWT
     */
    public String generateToken(Persona persona) {

        // Calcola la scadenza del token
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_YEAR, 1); //Imposta la data di scadenza di 1 giorno
        calendar.add(Calendar.MINUTE, 1);
        Date scadenza = calendar.getTime();

        // Genera il token con la chiave di firma
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withSubject(persona.getEmail())
                .withClaim("id", persona.getPersonaId())
                .withClaim("admin", persona.isAdmin())
                .withExpiresAt(scadenza)
                .sign(algorithm);
        return token;
    }

    /**
     * Verifica se il token JWT è valido.
     *
     * @param token il token JWT da verificare
     * @return true se il token  valido, false altrimenti
     */
    public boolean validateToken(String token) {
        try {
            // Verifica il token con la chiave di firma
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String email = decodedJWT.getSubject();
            Long personaId = decodedJWT.getClaim("id").asLong();
            boolean isAdmin = decodedJWT.getClaim("admin").asBoolean();

            System.out.println("Token valido per l'utente: " + email + " con ID: " + personaId + " admin: " + isAdmin);
            return true;
        } catch (JWTVerificationException e) {
            System.err.println("Token non valido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica le credenziali dell'utente e restituisce il token JWT se le
     * credenziali sono corrette.
     *
     * @param email    l'email dell'utente
     * @param password la password dell'utente
     * @return il token JWT se le credenziali sono corrette, null altrimenti
     */
    public String authenticateAndGenerateToken(String email, String password) {
        Persona persona = personaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Login non riuscito"));

        if (!passwordEncoder.matches(password, persona.getPassword())) {
            throw new IllegalArgumentException("Password non corretta");
        }
        System.out.println("Autenticazione riuscita per l'utente: " + email);
        return generateToken(persona);
    }
}
