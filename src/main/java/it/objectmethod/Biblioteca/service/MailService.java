package it.objectmethod.Biblioteca.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "mailService")
public class MailService {

    private Environment env;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(Environment env) {
        this.env = env;
    }

    public void sendMail(String mailAddress, String title, String mailMessage) {
        log.info(this.getClass().getName() + ".sendMail Start!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailAddress);
        message.setSubject(title);
        message.setText(mailMessage);

        javaMailSender.send(message);

        log.info(this.getClass().getName() + ".sendMail End!");
    }
}
