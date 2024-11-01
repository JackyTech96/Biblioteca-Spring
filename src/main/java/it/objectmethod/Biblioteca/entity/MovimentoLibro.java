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

    @Column(name = "stato")
    private String stato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id")
    private Utente utente;

}
