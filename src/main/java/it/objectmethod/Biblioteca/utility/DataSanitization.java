package it.objectmethod.Biblioteca.utility;

import it.objectmethod.Biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSanitization {
    @Autowired
    private LibroService libroService;

    //    @Scheduled(cron = "0 */1 * * * *")
    public void sanitizeDatabase() {
        libroService.sanitizeLibriByIsbn();
    }
}
