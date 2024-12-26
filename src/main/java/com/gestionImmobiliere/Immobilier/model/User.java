package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestionImmobiliere.Immobilier.enumeration.Genre;
import com.gestionImmobiliere.Immobilier.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String tel;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true)
    private String password;

    @Column(unique = true, length = 7)
    private String slug;

    @Column(nullable = true)
    private String avatar;

    @Column(nullable = true)
    private Genre gender = Genre.AUTRE;

    @Column(nullable = true)
    private String slugOrganisme;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idOrganisme")
    private Organisme organisme;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Avis> avis;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Contrat> contrats;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<ClauseContrat> clauseContrats;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Echange> echanges;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Declaration> declarations;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Rendezvous> rendezvous;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Set<Demande> Demande;
}
