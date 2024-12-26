package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestionImmobiliere.Immobilier.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeamande;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idAnnonce")
    private Annonce annonce;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idOrganisme")
    private Organisme organisme;

    @Column(nullable = false)
    private LocalDateTime dateDemande = LocalDateTime.now();

    //"En attente", "Acceptée", "Refusée"
    @Column(nullable = false)
    private String statut = "En attente";

    //Immeuble/Appartment
    private String type;

    private String ville;

    private String quartier;

    @Column(nullable = false)
    private String slug;

    @Column(length = 500)
    private String message;

    @Column(nullable = false)
    private String slugOrganisme;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
    }
}

