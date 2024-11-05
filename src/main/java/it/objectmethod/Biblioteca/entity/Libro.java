package it.objectmethod.Biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libro_id")
    private Long libroId;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "autore")
    private String autore;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "genere")
    private String genere;

    @Column(name = "editore")
    private String editore;

    @Column(name = "anno_pubblicazione")
    private Year annoPubblicazione;

    @Column(name = "copie")
    private Integer copie;

//    public List<String> allProperties() {
//        String libroId = String.valueOf(this.libroId);
//        String titolo = this.titolo;
//        String autore = this.autore;
//        String isbn = this.isbn;
//        String genere = this.genere;
//        String editore = this.editore;
//        String annoPubblicazione = String.valueOf(this.annoPubblicazione);
//        String copie = String.valueOf(this.copie);
//        return List.of(libroId, titolo, autore, isbn, genere, editore, annoPubblicazione, copie);
//    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "libro")
    private List<Prenotazione> prenotazioni;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "libro")
    private List<MovimentoLibro> movimentiLibro;

}
