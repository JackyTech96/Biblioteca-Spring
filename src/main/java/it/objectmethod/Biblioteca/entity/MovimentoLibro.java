package it.objectmethod.Biblioteca.entity;

import it.objectmethod.Biblioteca.enums.StatoMovimento;
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
@Table(name = "movimento_libro")
public class MovimentoLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimento_id")
    private Long movimentoLibroId;

    @Column(name = "data_prestito")
    private Date dataPrestito;

    @Column(name = "data_scadenza_restituzione")
    private Date dataScadenzaRestituzione;

    @Column(name = "data_restituzione")
    private Date dataRestituzione;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoMovimento stato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

}
