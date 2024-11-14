package it.objectmethod.Biblioteca.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Long personaId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "admin", columnDefinition = "boolean default false")
    private Boolean isAdmin;

    @Column(name = "telefono")
    @Nullable
    private String telefono;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    private List<Utente> utenti;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    private List<Personale> personale;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "persona")
    private List<PersonaIndirizzo> indirizzi;


}
