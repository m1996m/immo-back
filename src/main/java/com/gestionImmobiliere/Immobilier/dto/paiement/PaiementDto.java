package com.gestionImmobiliere.Immobilier.dto.paiement;

import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.model.FraisAgence;
import com.gestionImmobiliere.Immobilier.model.Paiement;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaiementDto {
    private long idPaiement;
    private double montant;
    private Date datePaiement;
    private String modePaiement;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idFacture;

    public Paiement create(PaiementDto dto, Facture facture){
        Paiement paiement = new Paiement();
        paiement.setModePaiement(dto.getModePaiement());
        paiement.setMontant(dto.getMontant());
        paiement.setSlugOrganisme(dto.getSlugOrganisme());
        paiement.setFacture(facture);

        return paiement;
   }

    public Paiement update(PaiementDto dto, Paiement paiement){
        paiement.setModePaiement(dto.getModePaiement());
        paiement.setMontant(dto.getMontant());

        return paiement;
    }
}
