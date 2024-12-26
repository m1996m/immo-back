package com.gestionImmobiliere.Immobilier.dto.fraisAgence;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FraisAgenceDto {
    private long idFraisAgence;
    private double montant;
    private String type;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idContrat;

    public FraisAgence create(FraisAgenceDto dto, Contrat contrat){
        FraisAgence fraisAgence = new FraisAgence();
        fraisAgence.setMontant(dto.getMontant());
        fraisAgence.setType(dto.getType());
        fraisAgence.setSlugOrganisme(dto.getSlugOrganisme());
        fraisAgence.setContrat(contrat);

        return fraisAgence;
   }

    public FraisAgence update(FraisAgenceDto dto, FraisAgence fraisAgence){
        fraisAgence.setMontant(dto.getMontant());
        fraisAgence.setType(dto.getType());

        return fraisAgence;
    }
}
