package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestionImmobiliere.Immobilier.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnnonce;

    @Column(nullable = false)
    private Date datePublication;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    //vente/location
    @Column(nullable = false)
    private String type;

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
    @JoinColumn(name = "idOrganisme")
    private Organisme organisme;

    @JsonManagedReference
    @OneToMany(mappedBy = "annonce")
    private Set<Echange> echanges;

    @JsonManagedReference
    @OneToMany(mappedBy = "annonce")
    private Set<Demande> demandes;
}
