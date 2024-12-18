package it.objectmethod.Biblioteca.entity;

import it.objectmethod.Biblioteca.enums.NomeRuolo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ruolo")
public class Ruolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ruolo_id")
    private Long ruoloId;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome_ruolo")
    private NomeRuolo nomeRuolo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ruolo")
    private List<Personale> personale;
}
