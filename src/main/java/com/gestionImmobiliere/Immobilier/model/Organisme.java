package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
//@EntityListeners(HistoriqueListener.class)
public class Organisme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idOrganisme;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String tel;

    @Column(unique = true, length = 7, updatable = false)
    private String slug;

    @Column(nullable = true)
    private String image;

    @PrePersist
    public void generateSlugBeforePersist() {
        if (this.slug == null || this.slug.isEmpty()) {
            this.slug = SlugGenerator.generateSlug();
        }
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<User> users;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<Bien> biens;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<ClauseContrat> clauseContrats;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<Echange> echanges;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<Declaration> declarations;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<Demande> demandes;

    @JsonManagedReference
    @OneToMany(mappedBy = "organisme")
    private Set<Annonce> annonces;
}
