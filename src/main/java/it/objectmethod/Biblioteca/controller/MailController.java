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
    public void sendMail() {
        mailService.sendMail("giulia.radaelli@objectmethod.it", "\uD83C\uDF1F Benvenuta su OnlyFans! \uD83C\uDF1F", "Ciao Giulia,\n" +
                "\n" +
                "Siamo entusiasti di darti il benvenuto nella nostra esclusiva community su OnlyFans! \uD83C\uDF89\n" +
                "\n" +
                "Hai appena aperto la porta a un mondo di contenuti unici, esperienze esclusive e connessioni autentiche. Qui, ogni momento è pensato per essere speciale, e il tuo viaggio inizia ora.\n" +
                "\n" +
                "\uD83D\uDD25 Cosa ti aspetta?\n" +
                "\n" +
                "Accesso a contenuti creati solo per te.\n" +
                "Interazioni dirette e personali con i tuoi creator preferiti.\n" +
                "Un luogo dove puoi essere te stessa e sentirti libera di esplorare.\n" +
                "\uD83D\uDC8C Hai domande o dubbi?\n" +
                "Il nostro team è sempre qui per te. Rispondici direttamente a questa email, e saremo felici di aiutarti.\n" +
                "\n" +
                "Siamo certi che troverai in OnlyFans uno spazio dove il tuo desiderio di intrattenimento incontra l’eccellenza. Preparati a momenti indimenticabili...\n" +
                "\n" +
                "Benvenuta a bordo, Giulia. Il meglio deve ancora venire. \uD83D\uDE09\n" +
                "\n" +
                "Con passione,\n" +
                "Il Team di OnlyFans\n" +
                "\n" +
                "P.S.: Assicurati di completare il tuo profilo e dare un’occhiata ai creator di tendenza. Non vediamo l’ora di rendere ogni giorno speciale per te!");
    }
}
