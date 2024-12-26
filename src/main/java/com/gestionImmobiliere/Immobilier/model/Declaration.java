package com.gestionImmobiliere.Immobilier.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gestionImmobiliere.Immobilier.global.SlugGenerator;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDeclaration;

    @Column(nullable = false)
    private String raison;

    private boolean statut = false;

    @Column(nullable = false)
    private Date dateDeclaration = new Date();

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
    @JoinColumn(name = "idUser")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idOrganisme")
    private Organisme organisme;
}
