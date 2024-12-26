package com.gestionImmobiliere.Immobilier.dto.demande;

import com.gestionImmobiliere.Immobilier.model.Annonce;
import com.gestionImmobiliere.Immobilier.model.Demande;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandeResponseDto {
    private Long id;
    private LocalDateTime dateDemande;
    private String statut;
    private String type;
    private String ville;
    private String quartier;
    private String slug;
    private String message;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idUser;
    private long idOrganisme;
    private long idAnnonce;
}
