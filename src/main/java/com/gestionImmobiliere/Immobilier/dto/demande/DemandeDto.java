package com.gestionImmobiliere.Immobilier.dto.demande;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DemandeDto {
    private Long idDemande;
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

    public Demande create(DemandeDto dto, User user, Organisme organisme, Annonce annonce){
        Demande demande = new Demande();
        demande.setStatut(dto.getStatut());
        demande.setType(dto.getType());
        demande.setQuartier(dto.getQuartier());
        demande.setMessage(dto.getMessage());
        demande.setSlugOrganisme(dto.getSlugOrganisme());
        demande.setOrganisme(organisme);
        demande.setUser(user);
        demande.setAnnonce(annonce);

        return demande;
   }

    public Demande update(DemandeDto dto, Demande demande){
        demande.setType(dto.getType());
        demande.setQuartier(dto.getQuartier());
        demande.setMessage(dto.getMessage());

        return demande;
    }

    public Demande updateStatut(DemandeDto dto, Demande demande){
        demande.setStatut(dto.getStatut());

        return demande;
    }
}
