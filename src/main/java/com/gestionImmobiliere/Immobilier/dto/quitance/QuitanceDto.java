package com.gestionImmobiliere.Immobilier.dto.quitance;

import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.Quitance;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuitanceDto {
    private long idQuitance;
    private Date datePaiement;
    private double montantPaye;
    private String slug;
    private String periode;
    private String slugOrganisme;
    private long idOrganisme;
    private long idContrat;
    private long numero;

    public Quitance create(QuitanceDto dto, Contrat contrat){
        Quitance quitance = new Quitance();
        quitance.setDatePaiement(dto.getDatePaiement());
        quitance.setMontantPaye(dto.getMontantPaye());
        quitance.setSlugOrganisme(dto.getSlugOrganisme());
        quitance.setPeriode(dto.getPeriode());
        quitance.setNumero(dto.getNumero());
        quitance.setContrat(contrat);

        return quitance;
   }

    public Quitance update(QuitanceDto dto, Quitance quitance){
        quitance.setDatePaiement(dto.getDatePaiement());
        quitance.setMontantPaye(dto.getMontantPaye());
        quitance.setPeriode(dto.getPeriode());
        quitance.setNumero(dto.getNumero());

        return quitance;
    }
}
