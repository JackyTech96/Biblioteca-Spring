package it.objectmethod.Biblioteca.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utente_id")
    private Long utenteId;

    @Column(name = "inizio_iscrizione")
    private Date inizioIscrizione;

    @Column(name = "fine_iscrizione")
    @Nullable
    private Date fineIscrizione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "utente")
    private List<MovimentoLibro> movimentiLibro;
}
