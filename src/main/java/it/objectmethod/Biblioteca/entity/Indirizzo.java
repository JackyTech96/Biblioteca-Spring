package it.objectmethod.Biblioteca.entity;

import it.objectmethod.Biblioteca.enums.TipoVia;
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
@Table(name = "indirizzo")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "indirizzo_id")
    private Long indirizzoId;

    @Column(name = "nazione")
    private String nazione;

    @Column(name = "citta")
    private String citta;

    @Column(name = "via")
    private String via;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_via")
    private TipoVia tipoVia;

    @Column(name = "numero_civico")
    private String numeroCivico;

    @Column(name = "cap")
    private String cap;

    @Column(name = "tipo_indirizzo")
    private String tipoIndirizzo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "indirizzo")
    private List<PersonaIndirizzo> personaIndirizzi;
}
