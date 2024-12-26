package com.gestionImmobiliere.Immobilier.dto.contrat;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContratDto {
    private long idContrat;
    private Date dateContrat;
    private Date dateFinContrat;
    private String type;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idBien;
    private long idUser;
    private long idClauseContrat;
    private Date dateEcheance;
    private double montantMensuel;
    private String statut = "ACTIF";

    public Contrat create(ContratDto dto, User user, Bien bien, ClauseContrat clause){
        Contrat contrat = new Contrat();
        contrat.setDateContrat(dto.getDateContrat());
        contrat.setDateFinContrat(dto.getDateFinContrat());
        contrat.setType(dto.getType());
        contrat.setMontantMensuel(dto.getMontantMensuel());
        contrat.setDateEcheance(dto.getDateEcheance());
        contrat.setSlugOrganisme(dto.getSlugOrganisme());
        contrat.setClauseContrat(clause);
        contrat.setBien(bien);
        contrat.setUser(user);

        return contrat;
   }

    public Contrat update(ContratDto dto, Bien bien, ClauseContrat clause, Contrat contrat){
        contrat.setDateContrat(dto.getDateContrat());
        contrat.setDateFinContrat(dto.getDateFinContrat());
        contrat.setType(dto.getType());
        contrat.setClauseContrat(clause);
        contrat.setBien(bien);

        return contrat;
    }

    public Contrat activeOrDesactiveContrat(String statut, Contrat contrat){
        contrat.setStatut(statut);

        return contrat;
    }
}
