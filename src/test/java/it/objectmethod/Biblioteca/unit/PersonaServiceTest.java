package it.objectmethod.Biblioteca.unit;

import it.objectmethod.Biblioteca.dto.PersonaDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.mapper.PersonaMapper;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.PersonaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@MockitoSettings
public class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private PersonaMapper personaMapper;

    @InjectMocks
    private PersonaService personaService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    static final PersonaDto personaDto = PersonaDto.builder()
            .personaId(1L)
            .nome("nome")
            .email("email")
            .telefono("telefono")
            .password("password")
            .isAdmin(false)
            .build();

    static final Persona personaEntity = Persona.builder()
            .personaId(1L)
            .nome("nome")
            .email("email")
            .telefono("telefono")
            .password("password")
            .isAdmin(false)
            .build();

    @Test
    void findAll() {

        // ARRANGE: Prepara i dati di test e le aspettative
        final ApiResponse<List<PersonaDto>> expected = new ApiResponse<>();
        expected.setMessage("Lista persone");
        final List<PersonaDto> personaDtoList = List.of(personaDto);
        expected.setData(personaDtoList);
        final List<Persona> personaList = List.of(personaEntity);

        // Configura i mock per restituire i dati preparati
        when(personaRepository.findAll()).thenReturn(personaList);
        when(personaMapper.personaListToPersonaDtoList(personaList)).thenReturn(personaDtoList);

        // ACT: Esegui il metodo da testare
        final ApiResponse<List<PersonaDto>> actual = personaService.findAll();

        // ASSERT: Verifica che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void findById() {
        // ARRANGE: Prepara i dati di test e le aspettative
        final ApiResponse<PersonaDto> expected = new ApiResponse<>();
        expected.setData(personaDto);
        expected.setMessage("Persona:");

        // Configura i mock per restituire i dati preparati
        when(personaRepository.findById(1L)).thenReturn(Optional.of(personaEntity));
        when(personaMapper.personaToPersonaDto(personaEntity)).thenReturn(personaDto);

        // ACT: Esegui il metodo da testare
        final ApiResponse<PersonaDto> actual = personaService.getPersonaById(1L);

        // ASSERT: Verifica che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void shouldCreatePersona() {
        // ARRANGE: Prepara i dati di test e le aspettative
        final ApiResponse<PersonaDto> expected = new ApiResponse<>();
        expected.setMessage("Persona creata con successo:");
        expected.setData(personaDto);

        final PersonaDto personaDtoToSave = PersonaDto.builder()
                .personaId(null) //null perchè viene assegnato automaticamente
                .nome("nome")
                .email("email")
                .telefono("telefono")
                .password("password")
                .isAdmin(false)
                .build();

        // Configura i mock per restituire i dati preparati
        when(personaMapper.personaDtoToPersona(personaDtoToSave)).thenReturn(personaEntity);
        when(personaRepository.save(personaEntity)).thenReturn(personaEntity);
        when(personaMapper.personaToPersonaDto(personaEntity)).thenReturn(personaDto);

        // ACT: Esegui il metodo da testare
        final ApiResponse<PersonaDto> actual = personaService.createPersona(personaDtoToSave);

        // ASSERT: Verifica che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("data.password")
                .isEqualTo(expected);
    }

    @Test
    void shouldUpdatePersona() {
        // ARRANGE: Prepara i dati di test
        final Persona personaOriginal = Persona.builder()
                .personaId(1L)
                .nome("originalName")
                .email("originalEmail")
                .telefono("originalPhone")
                .password("originalPassword")
                .isAdmin(false)
                .build();

        final PersonaDto personaUpdates = PersonaDto.builder()
                .personaId(1L)
                .nome("updatedName")
                .email("updatedEmail")
                .telefono("updatedPhone")
                .password("updatedPassword")
                .isAdmin(true)
                .build();

        final Persona personaUpdated = Persona.builder()
                .personaId(1L)
                .nome("updatedName")
                .email("updatedEmail")
                .telefono("updatedPhone")
                .password("updatedPassword")
                .isAdmin(true)
                .build();

        final PersonaDto personaDtoUpdated = PersonaDto.builder()
                .personaId(1L)
                .nome("updatedName")
                .email("updatedEmail")
                .telefono("updatedPhone")
                .password("updatedPassword")
                .isAdmin(true)
                .build();

        final ApiResponse<PersonaDto> expected = new ApiResponse<>();
        expected.setMessage("Persona aggiornata con successo");
        expected.setData(personaDtoUpdated);

        // Configura i mock per restituire i dati preparati
        when(personaRepository.findById(1L)).thenReturn(Optional.of(personaOriginal));
        when(personaMapper.updatePersona(personaOriginal, personaUpdates)).thenReturn(personaUpdated);
        when(personaRepository.save(personaOriginal)).thenReturn(personaUpdated);
        when(personaMapper.personaToPersonaDto(personaUpdated)).thenReturn(personaDtoUpdated);

        // ACT: Esegui il metodo da testare
        final ApiResponse<PersonaDto> actual = personaService.updatePersona(personaUpdates, 1L);

        // ASSERT: Verifica che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("data.password") // Ignora la password per motivi di sicurezza
                .isEqualTo(expected);
    }
}