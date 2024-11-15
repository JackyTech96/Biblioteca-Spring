package it.objectmethod.Biblioteca.security.token;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    @Value("${password.jwt}")
    private String secretKey;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonaRepository personaRepository;

    private String createToken(final Map<String, Object> claims, final String subject) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(subject)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(final AuthorizationRequest request) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("email", request.getEmail());
        claims.put("admin", request.getIsAdmin());
        return createToken(claims, request.getId().toString());
    }

//    public String authenticateAndGenerateToken(final AuthorizationRequest request, final String email, final String password) {
//        Persona persona = personaRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("Login non riuscito"));
//
//        if (!passwordEncoder.matches(password, (persona.getPassword()))) {
//            throw new IllegalArgumentException("Login non riuscito");
//        }
//        System.out.println("Autenticazione riuscita per l'utente: " + email);
//        return generateToken(request);
//    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public Boolean isTokenValid(String token) {
        final String subject = extractAllClaims(token).getSubject();
        return (subject != null && !isTokenExpired(token));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean adminFromClaims(String token) {
        return extractClaim(token, claims -> claims.get("admin", Boolean.class));
    }

    public <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
}
