package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestionImmobiliere.Immobilier.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvis;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @Column(nullable = false)
    private int note;

    @Column(unique = true, length = 7, updatable = false)
    private String slug;

    @Column(nullable = false)
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
    @JoinColumn(name = "idBien")
    private Bien bien;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
