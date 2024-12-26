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
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idContrat;

    @Column(nullable = false)
    private Date dateContrat;

    private Date dateFinContrat;

    //'location' ou 'vente'
    @Column(nullable = false)
    private String type;

    //'ACTIF' ou 'iNACTIF'
    @Column(nullable = false)
    private String statut = "ACTIF";

    @Column(nullable = false)
    private Date dateEcheance;

    @Column(nullable = false)
    private double montantMensuel;

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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idClauseContrat")
    private ClauseContrat clauseContrat;

    @JsonManagedReference
    @OneToMany(mappedBy = "contrat")
    private Set<Quitance> quitances;

    @JsonManagedReference
    @OneToMany(mappedBy = "contrat")
    private Set<Facture> factures;

    @JsonManagedReference
    @OneToMany(mappedBy = "contrat")
    private Set<FraisAgence> fraisAgences;
}
