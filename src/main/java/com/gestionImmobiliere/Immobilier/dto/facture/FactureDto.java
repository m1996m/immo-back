package com.gestionImmobiliere.Immobilier.dto.facture;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FactureDto {
    private long idFacture;
    private double montantPaye;
    private String slug;
    private String periode;
    private String slugOrganisme;
    private long idOrganisme;
    private long idContrat;
    private long numero;
    private double montantRestant = 0;


    public Facture create(FactureDto dto, Contrat contrat){
        Facture facture = new Facture();
        facture.setMontantPaye(dto.getMontantPaye());
        facture.setSlugOrganisme(dto.getSlugOrganisme());
        facture.setPeriode(dto.getPeriode());
        facture.setMontantRestant(dto.getMontantRestant());
        facture.setNumero(dto.getNumero());
        facture.setContrat(contrat);

        return facture;
   }

    public Facture update(FactureDto dto, Facture facture){
        facture.setMontantPaye(dto.getMontantPaye());
        facture.setMontantRestant(dto.getMontantRestant());
        facture.setNumero(dto.getNumero());

        return facture;
    }
}
