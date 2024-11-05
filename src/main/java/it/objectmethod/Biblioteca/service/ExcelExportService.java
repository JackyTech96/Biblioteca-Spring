package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.LibroDto;
import it.objectmethod.Biblioteca.dto.LibroExportDto;
import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.mapper.LibroMapper;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.repository.LibroRepository;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.utility.FileExportUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelExportService {
    @Autowired
    private FileExportUtility fileExportUtility;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private LibroMapper libroMapper;

    //    @Scheduled(cron = "0 */1 * * * *")
    public void exportPersonasToExcel() throws IOException {
        List<PersonaDto> personas = personaMapper.personaListToPersonaDtoList(personaRepository.findAll());
        Map<String, List<PersonaDto>> mapData = new HashMap<>();
        mapData.put("Personas", personas);

        fileExportUtility.createFileWithSheets("ExportDataPersona", mapData);
    }

    //    @Scheduled(cron = "0 */1 * * * *")
    public void exportLibrosToExcel() throws IOException {
        List<LibroExportDto> libros = libroMapper.libriToLibroExportDtoList(libroRepository.findAll());
        Map<String, List<LibroExportDto>> mapData = new HashMap<>();
        mapData.put("Libri", libros);

        fileExportUtility.createFileWithSheets("ExportDataLibro", mapData);
    }
}
