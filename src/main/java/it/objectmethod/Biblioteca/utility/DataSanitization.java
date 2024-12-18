package it.objectmethod.Biblioteca.utility;

import it.objectmethod.Biblioteca.service.LibroService;
import it.objectmethod.Biblioteca.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataSanitization {
    @Autowired
    private LibroService libroService;
    @Autowired
    private PersonaService personaService;

    //    @Scheduled(cron = "0 */1 * * * *")
    public void sanitizeDatabaseLibros() {
        libroService.sanitizeLibriByIsbn();
    }

    //    @Scheduled(cron = "0 */1 * * * *")
    public void sanitizeDatabasePersonas() {
        personaService.sanitizePersonasByNome();
    }
}
