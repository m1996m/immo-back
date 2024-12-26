package com.gestionImmobiliere.Immobilier.dto.contrat;

import com.gestionImmobiliere.Immobilier.dto.facture.FactureDto;
import com.gestionImmobiliere.Immobilier.dto.fraisAgence.FraisAgenceDto;
import com.gestionImmobiliere.Immobilier.dto.quitance.QuitanceDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContratResponseDto {
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
    private List<QuitanceDto> quitances;
    private List<FactureDto> factures;
    private List<FraisAgenceDto> fraisAgences;
}
