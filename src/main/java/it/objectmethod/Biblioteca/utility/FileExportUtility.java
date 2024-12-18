package it.objectmethod.Biblioteca.utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Component
public class FileExportUtility {

    /**
     * Crea una directory denominata "file-storage" se non esiste già.
     */
    private void createDirectory() {
        // Crea un oggetto File che rappresenta la directory "file-storage"
        File directory = new File("file-storage");
        // Controlla se la directory esiste già
        if (!directory.exists()) {
            // Crea la directory se non esiste
            directory.mkdirs();
        }
    }

    /**
     * Crea un file Excel e popola fogli di lavoro con i dati forniti.
     *
     * @param filename Nome del file Excel da creare.
     * @param mapData  Mappa di nomi di fogli e dati corrispondenti (List<Object>).
     * @throws IOException Se si verifica un errore durante la scrittura del file.
     */

    public <T> void createFileWithSheets(final String filename, final Map<String, List<T>> mapData)
            throws IOException {
        // Crea la directory file-storage se non esiste
        createDirectory();

        // Crea un nuovo workbook Excel
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Itera su ogni voce nella mappa fornita
            for (Map.Entry<String, List<T>> entry : mapData.entrySet()) {
                String sheetName = entry.getKey(); // Ottiene il nome del foglio
                List<T> data = entry.getValue(); // Ottiene i dati per il foglio

                // Crea un nuovo foglio di lavoro con il nome fornito
                Sheet sheet = workbook.createSheet(sheetName);

                // Controlla se ci sono dati da scrivere
                if (!data.isEmpty()) {
                    Class<?> clazz = data.get(0).getClass(); // Ottiene la classe del primo oggetto della lista
                    Field[] fields = clazz.getDeclaredFields(); // Ottiene tutti i campi della classe

                    // Crea la riga dell'intestazione del foglio
                    Row headerRow = sheet.createRow(0); // Crea la prima riga per l'intestazione
                    for (int i = 0; i < fields.length; i++) {
                        // Imposta il nome del campo come intestazione
                        headerRow.createCell(i).setCellValue(fields[i].getName());
                    }

                    int rowIndex = 1; // Inizia dalla riga successiva per i dati
                    // Itera su ogni oggetto nella lista dei dati
                    for (T obj : data) {
                        Row row = sheet.createRow(rowIndex++); // Crea una nuova riga per ogni oggetto
                        for (int i = 0; i < fields.length; i++) {
                            fields[i].setAccessible(true); // Permette di accedere a campi privati
                            try {
                                // Ottiene il valore del campo dall'oggetto corrente
                                Object fieldValue = fields[i].get(obj);
                                // Imposta il valore nella cella corrispondente, gestendo valori nulli
                                row.createCell(i).setCellValue(fieldValue != null ? fieldValue.toString() : "");
                            } catch (IllegalAccessException e) {
                                // Gestione dell'eccezione se non si riesce ad accedere al campo
                                System.err.println("Eccezione durante l'accesso al campo: " + fields[i].getName());
                            }
                        }
                    }
                }
            }
            // Scrive il workbook su un file Excel
            try (FileOutputStream outputStream = new FileOutputStream("file-storage/" + filename + ".xlsx")) {
                workbook.write(outputStream); // Scrive il contenuto del workbook nel file

            }
        }
    }
}