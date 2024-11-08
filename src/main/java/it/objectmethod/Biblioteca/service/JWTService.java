package it.objectmethod.Biblioteca.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import it.objectmethod.Biblioteca.constant.Constants;
import it.objectmethod.Biblioteca.entity.Persona;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class JWTService {

    /**
     * Genera un token JWT con scadenza di 1 giorno e chiave di firma
     * definita nella costante Constants.JWT_SECRET.
     *
     * @return il token JWT
     */
    public String generateToken(Persona persona) {

        // Calcola la scadenza del token
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); //Imposta la data di scadenza di 1 giorno
        Date scadenza = calendar.getTime();

        // Genera il token con la chiave di firma
        Algorithm algorithm = Algorithm.HMAC256(Constants.JWT_SECRET);
        String token = JWT.create()
                .withSubject(persona.getEmail())
                .withClaim("id", persona.getPersonaId())
                .withExpiresAt(scadenza)
                .sign(algorithm);
        return token;
    }

    /**
     * Verifica se il token JWT  valido.
     *
     * @param token il token JWT da verificare
     * @return true se il token  valido, false altrimenti
     */
    public boolean validateToken(String token) {
        try {
            // Verifica il token con la chiave di firma
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constants.JWT_SECRET))
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String email = decodedJWT.getSubject();
            Long personaId = decodedJWT.getClaim("id").asLong();

            System.out.println("Token valido per l'utente: " + email + " con ID: " + personaId);
            return true;
        } catch (JWTVerificationException e) {
            System.err.println("Token non valido: " + e.getMessage());
            return false;
        }
    }
}
