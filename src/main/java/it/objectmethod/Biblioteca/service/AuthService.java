package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.excepction.LoginFailedException;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.security.token.AuthorizationRequest;
import it.objectmethod.Biblioteca.security.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;

    /**
     * Genera un token JWT per l'utente.
     *
     * @param request l'oggetto contenente le informazioni dell'utente
     * @return il token JWT
     */
    public String createToken(final AuthorizationRequest request) {
        return jwtTokenProvider.generateToken(request);
    }

    /**
     * Verifica le credenziali dell'utente e genera un token JWT se le
     * credenziali sono corrette.
     *
     * @param request l'oggetto contenente le informazioni dell'utente
     * @return un oggetto ApiResponse contenente il token JWT e un messaggio di
     * esito
     */
    public ApiResponse<String> login(final AuthorizationRequest request) {
        // Ottengo la persona con l'email
        final Persona persona = personaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ElementNotFoundException("Utente non trovato"));

        // Verifico le credenziali
        if (!personaRepository.existsByPassword(request.getPassword())) {
            throw new LoginFailedException("chi cazzo sei?");
        }

        // Genero un token JWT
        final AuthorizationRequest authorizationRequest = personaMapper.toRequestForToken(persona);
        final String token = jwtTokenProvider.generateToken(authorizationRequest);

        // Ritorno un oggetto ApiResponse contenente il token JWT e un messaggio di esito
        return ApiResponse.<String>builder()
                .data(token)
                .message("Login riuscito")
                .build();
    }

    /**
     * Verifica se il token JWT  valido.
     *
     * @param token il token JWT da verificare
     * @return true se il token  valido, false altrimenti
     */
    public boolean isValid(final String token) {
        return jwtTokenProvider.isTokenValid(token);
    }
}
