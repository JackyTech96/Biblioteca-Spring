package it.objectmethod.Biblioteca.entity;

import it.objectmethod.Biblioteca.enums.StatoPrenotazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prenotazione")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prenotazione_id")
    private Long prenotazioneId;

    @Column(name = "data_prenotazione")
    private Date dataPrenotazione;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoPrenotazione stato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

}
