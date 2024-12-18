package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.dto.PersonaleDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.entity.Personale;
import it.objectmethod.Biblioteca.entity.Ruolo;
import it.objectmethod.Biblioteca.excepction.ElementNotFoundException;
import it.objectmethod.Biblioteca.excepction.OperationNotAllowedException;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.mapper.PersonaleMapper;
import it.objectmethod.Biblioteca.param.PersonaleParams;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.repository.PersonaleRepository;
import it.objectmethod.Biblioteca.repository.RuoloRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaleService {

    @Autowired
    private PersonaleRepository personaleRepository;

    @Autowired
    private PersonaleMapper personaleMapper;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaMapper personaMapper;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<PersonaleDto> getAllPersonale() {
        return personaleMapper.personaleListToPersonaleDtoList(personaleRepository.findAll());
    }

    public ApiResponse<PersonaleDto> createPersonaleConPersona(final PersonaleDto personaleDto) {
        // Creazione della persona
        Persona persona = new Persona();
        persona.setNome(personaleDto.getNome());
        persona.setEmail(personaleDto.getEmail());
        persona.setTelefono(personaleDto.getTelefono());
        persona.setPassword(passwordEncoder.encode(personaleDto.getPassword()));

        persona = personaRepository.save(persona);


        // Creazione e salvataggio del ruolo
        Ruolo ruolo = new Ruolo();
        ruolo.setNomeRuolo(personaleDto.getRuolo().getNomeRuolo());
        ruolo = ruoloRepository.save(ruolo);

        // Creazione del personale e impostazione delle relazioni
        Personale personale = personaleMapper.personaleDtoToPersonale(personaleDto);
        personale.setPersona(persona);
        personale.setRuolo(ruolo);

        // Salvataggio del personale
        personaleRepository.save(personale);
        PersonaleDto personaleDtoCreated = personaleMapper.personaleToPersonaleDto(personale);
        return new ApiResponse<>("Personale creato con successo", personaleDtoCreated);
    }

    public PersonaleDto findPersonaleById(final Long id) {
        try {
            return personaleMapper.personaleToPersonaleDto(personaleRepository.findById(id).get());
        } catch (RuntimeException e) {
            throw new ElementNotFoundException("Personale non trovato");
        }
    }

    public List<PersonaleDto> findPersonaleByNomeRuolo(final String nomeRuolo) {
        List<Personale> personaleList = personaleRepository.findByRuolo_NomeRuolo(nomeRuolo);
        if (personaleList.isEmpty()) {
            throw new ElementNotFoundException("Nessun personale trovato con il ruolo " + nomeRuolo);
        }
        return personaleMapper.personaleListToPersonaleDtoList(personaleList);
    }

    public List<PersonaleDto> findWithSpecification(final PersonaleParams params) {
        List<Personale> personaleList = personaleRepository.findAll(params.toSpecification());
        if (personaleList.isEmpty()) {
            throw new ElementNotFoundException("Nessun personale trovato con i criteri specificati.");
        }
        return personaleMapper.personaleListToPersonaleDtoList(personaleList);
    }

//    public List<PersonaleDto> findPersonaleByNomeRuolo(final PersonaleParams params) {
//        return personaleMapper.personaleListToPersonaleDtoList(personaleRepository.findAll(params.toSpecification()));
//    }


}
