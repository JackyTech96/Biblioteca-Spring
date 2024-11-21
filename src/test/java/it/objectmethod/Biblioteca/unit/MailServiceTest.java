package it.objectmethod.Biblioteca.unit;

import it.objectmethod.Biblioteca.service.MailService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@MockitoSettings
public class MailServiceTest {


    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailService mailService;

    @Test
    void shouldSendEmail() {

        // Arrange: Configurazione del mock
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        // Act: Chiamata al metodo da testare
        mailService.sendMail("mailAddress", "subject", "text");

        // Assert: Verifica che il metodo send sia stato chiamato
        verify(javaMailSender, times(1)).send(Mockito.argThat(
                (SimpleMailMessage message) -> message.getTo() != null &&
                        message.getTo().length > 0 &&
                        message.getTo()[0].equals("mailAddress") &&
                        message.getSubject().equals("subject") &&
                        message.getText().equals("text")));
    }
}
