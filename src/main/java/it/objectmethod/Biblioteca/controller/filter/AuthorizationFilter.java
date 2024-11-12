package it.objectmethod.Biblioteca.controller.filter;

import it.objectmethod.Biblioteca.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@Component
@Order(2)
public class AuthorizationFilter implements Filter {

    @Autowired
    JwtService jwtService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Controllo se la richiesta e' per il login
        String path = request.getRequestURI();
        if (path.contains("login")) {
            filterChain.doFilter(request, response);
        } else {
            // Controllo l'header di autorizzazione
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token non presente");
            } else {
                // Estraggo il token dall'header
                String token = authHeader.substring(7);

                // Implemento la logica di validazione del token qui
                if (!jwtService.validateToken(token)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token non valido o scaduto");
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        }
    }
}
