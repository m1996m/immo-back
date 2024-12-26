package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestionImmobiliere.Immobilier.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private LocalDateTime date;

    // Exemple : "Annonce", "Message"
    @Column(nullable = false)
    private String entiteCible;

    @Column(nullable = false)
    private String slug;

    // Exemple : "Création", "Mise à jour", "Suppression"
    @Column(nullable = false)
    private String typeAction;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
}

