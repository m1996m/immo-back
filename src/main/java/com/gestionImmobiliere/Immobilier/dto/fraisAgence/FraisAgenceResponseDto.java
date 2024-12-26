package com.gestionImmobiliere.Immobilier.dto.fraisAgence;

import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.FraisAgence;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FraisAgenceResponseDto {
    private long idFraisAgence;
    private double montant;
    private String type;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idContrat;
}
