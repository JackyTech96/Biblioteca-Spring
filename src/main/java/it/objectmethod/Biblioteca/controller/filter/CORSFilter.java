package it.objectmethod.Biblioteca.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class CORSFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        // Converto i parametri della richiesta HTTP in un oggetto HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Imposto l'header CORS
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permette richieste da qualsiasi origine
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Metodi permessi
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Intestazioni permesse
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // Se il metodo richiesto è OPTIONS, allora restituisco l'HTTP Status 200 (OK)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // Passaggio al filter successivo
        filterChain.doFilter(request, response);
    }

}
