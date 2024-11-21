package it.objectmethod.Biblioteca.controller;

import it.objectmethod.Biblioteca.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("")
    public void sendMail() {// guarda come creare un template per le email. usa i tag xml tipo
        mailService.sendMail("mail@address", "title", "message");
    }
}
