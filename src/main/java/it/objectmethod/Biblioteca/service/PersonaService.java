package it.objectmethod.Biblioteca.service;

import io.micrometer.common.util.StringUtils;
import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.excepction.InvalidPersonaNameException;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<PersonaDto> findAll() {
        return personaMapper.personaListToPersonaDtoList(personaRepository.findAll());
    }

    public ApiResponse<PersonaDto> createPersona(final PersonaDto personaDto) {

        if (StringUtils.isBlank(personaDto.getPassword())) {
            throw new IllegalArgumentException("La password non puo essere vuota");
        }
        Persona persona = personaMapper.personaDtoToPersona(personaDto);
        persona.setPassword(passwordEncoder.encode(persona.getPassword()));
        PersonaDto personaDtoToSave = personaMapper.personaToPersonaDto(personaRepository.save(persona));
        return new ApiResponse<>("Persona creato con successo", personaDtoToSave);
    }


    public Persona getPersonaById(final Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(String.format("Persona con id %d non trovata", id)));
    }


    /**
     * Cancella le persone che non hanno il nome in maiuscolo e crea un backup in Excel
     * prima della sanificazione.
     */
    public void sanitizePersonasByNome() {
        // Recupera le persone che non hanno il nome in maiuscolo
        List<Persona> personasWithLowerCaseNames = personaRepository.findAllWithNomeStartingWithLowerCase();

        try {
            // Esporta le persone in un file Excel come copia di backup
            excelExportService.exportPersonasWithLowerCaseNamesToExcel();
            System.out.println("Backup delle persone eseguito con successo.");
        } catch (IOException e) {
            System.err.println("Errore durante l'esportazione delle persone: " + e.getMessage());
            return;
        }

        // Sanifica le persone non valide
        for (Persona persona : personasWithLowerCaseNames) {
            String nomeCorretto = excelExportService.capitalizeFirstLetter(persona.getNome());
            if (nomeCorretto == null || nomeCorretto.equals(persona.getNome())) {
                throw new InvalidPersonaNameException("Il nome non è stato correttamente sanificato per la persona con ID: " + persona.getPersonaId());
            }
            persona.setNome(nomeCorretto);
            personaRepository.save(persona);
        }

        System.out.println("Sanificazione delle persone eseguita con successo.");
    }
}
