package it.objectmethod.Biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "persona_indirizzo")
public class PersonaIndirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_indirizzo_id")
    private Long personaIndirizzoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indirizzo_id", nullable = false)
    private Indirizzo indirizzo;
}
