package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/export")
public class ExcelExportController {

   /*
    * Con lo schedule non serve più passare dal controller.
    * Verranno esportati automaticamente i dati delle persone
    * e dei libri.
    */
    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/persona")
    public String exportPersona() {
        try {
            excelExportService.exportPersonasToExcel();
            return "Esportazione effettuata con successo";
        } catch (IOException e) {
            return "Errore durante l'esportazione: " + e.getMessage();
        }
    }

    @GetMapping("/libro")
    public String exportLibro() {
        try {
            excelExportService.exportLibrosToExcel();
            return "Esportazione effettuata con successo";
        } catch (IOException e) {
            return "Errore durante l'esportazione: " + e.getMessage();
        }
    }
}
