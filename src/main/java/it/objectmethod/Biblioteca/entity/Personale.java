package it.objectmethod.Biblioteca.entity;

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
@Table(name = "personale")
public class Personale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personale_id")
    private Long personaleId;

    @Column(name = "data_assunzione")
    private Date dataAssunzione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruolo_id", nullable = false)
    private Ruolo ruolo;
}
