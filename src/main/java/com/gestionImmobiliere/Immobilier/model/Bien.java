package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestionImmobiliere.Immobilier.global.SlugGenerator;
import com.gestionImmobiliere.Immobilier.listener.listerMethode.event.HistoriqueListener;
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
//@EntityListeners(HistoriqueListener.class)
public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBien;

    // terrain/maison/appartement/immeuble/Studio
    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String adresse;

    private String surface;

    @Column(nullable = false)
    private String ville;

    private String description;

    @Column(nullable = false)
    private String slugOrganisme;

    @Column(nullable = false)
    private int NombrePiece;

    @Column(nullable = false)
    private double prix;

    @Column(unique = true, length = 7, updatable = false)
    private String slug;

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
    @OneToMany(mappedBy = "bien")
    private Set<Avis> avis;

    @JsonManagedReference
    @OneToMany(mappedBy = "bien")
    private Set<Contrat> contrats;

    @JsonManagedReference
    @OneToMany(mappedBy = "bien")
    private Set<Annonce> annonces;
}
