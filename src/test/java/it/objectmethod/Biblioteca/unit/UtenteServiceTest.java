package it.objectmethod.Biblioteca.unit;

import it.objectmethod.Biblioteca.dto.UtenteDto;
import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.entity.Utente;
import it.objectmethod.Biblioteca.mapper.UtenteMapper;
import it.objectmethod.Biblioteca.param.UtenteParams;
import it.objectmethod.Biblioteca.repository.PersonaRepository;
import it.objectmethod.Biblioteca.repository.UtenteRepository;
import it.objectmethod.Biblioteca.response.ApiResponse;
import it.objectmethod.Biblioteca.service.UtenteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
public class UtenteServiceTest {

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private UtenteMapper utenteMapper;

    @Mock
    private UtenteParams utenteParams;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UtenteService utenteService;

    // Calcolo la data di fine iscrizione, un anno dopo la data corrente
    private Date getDataFineIscrizione() {
        return Date.from(
                LocalDate.now()
                        .plusYears(1)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
    }

    // Creo un oggetto UtenteDto con dati di esempio
    private UtenteDto createUtenteDto() {
        return UtenteDto.builder()
                .nome("Mario")
                .email("mario@rossi.it")
                .telefono("123456789")
                .password("password")
                .inizioIscrizione(new Date())
                .fineIscrizione(getDataFineIscrizione())
                .build();
    }

    @Test
    void shouldCreateUtenteWithPersona() {
        // ARRANGE: Prepara i dati di test e le aspettative
        final ApiResponse<UtenteDto> expected = new ApiResponse<>("Operazione riuscita", createUtenteDto());

        // Persona mockata da salvare
        final Persona personaToSave = Persona.builder()
                .nome("Mario")
                .email("mario@rossi.it")
                .telefono("123456789")
                .password("password")
                .build();

        // UtenteDto da salvare
        final UtenteDto utenteDtoToSave = createUtenteDto();

        // Configuro i mock per i metodi repository e mapper
        when(personaRepository.save(any(Persona.class))).thenReturn(personaToSave);
        when(utenteMapper.utenteDtoToUtente(utenteDtoToSave)).thenReturn(new Utente());
        when(utenteRepository.save(any(Utente.class))).thenReturn(new Utente());
        when(utenteMapper.utenteToUtenteDto(any(Utente.class))).thenReturn(createUtenteDto());

        // ACT: Esegui il metodo da testare
        final ApiResponse<UtenteDto> actual = utenteService.creaUtenteConPersona(utenteDtoToSave);

        // ASSERT: Verifica che il risultato attuale corrisponda all'aspettativa
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("data.password", "data.inizioIscrizione")
                .isEqualTo(expected);
    }

    @Test
    void shouldReturnUtente_whenSearchBySpecification() {
        final List<Utente> utenteList = List.of(Utente.builder()
                        .utenteId(1L)
                        .inizioIscrizione(new Date())
                        .fineIscrizione(getDataFineIscrizione())
                        .persona(Persona.builder()
                                .personaId(1L)
                                .nome("nome1")
                                .email("email1")
                                .telefono("telefono1")
                                .password("password1")
                                .isAdmin(true)
                                .build())
                        .build(),
                Utente.builder()
                        .utenteId(2L)
                        .inizioIscrizione(new Date())
                        .fineIscrizione(getDataFineIscrizione())
                        .persona(Persona.builder()
                                .personaId(2L)
                                .nome("nome2")
                                .email("email2")
                                .telefono("telefono2")
                                .password("password2")
                                .isAdmin(false)
                                .build())
                        .build());

        when(utenteParams.getNome()).thenReturn("nome2");

        List<Utente> filteredUtenteList = utenteList.stream()
                .filter(utente -> utente.getPersona().getNome().equals(utenteParams.getNome()))
                .toList();

        List<UtenteDto> expected = List.of(UtenteDto.builder()
                .utenteId(2L)
                .personaId(2L)
                .nome("nome2")
                .email("email2")
                .telefono("telefono2")
                .password("password2")
                .isAdmin(false)
                .inizioIscrizione(filteredUtenteList.get(0).getInizioIscrizione())
                .fineIscrizione(filteredUtenteList.get(0).getFineIscrizione())
                .build());
        when(utenteMapper.utenteListToUtenteDtoList(filteredUtenteList)).thenReturn(expected);

        List<UtenteDto> actual = utenteService.findWithSpecification(utenteParams);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("password", "isAdmin", "inizioIscrizione", "fineIscrizione")
                .isEqualTo(expected);
    }

}