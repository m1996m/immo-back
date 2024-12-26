package com.gestionImmobiliere.Immobilier.dto.paiement;

import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.model.Paiement;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaiementResponseDto {
    private long idPaiement;
    private double montant;
    private Date datePaiement;
    private String modePaiement;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idFacture;
}
