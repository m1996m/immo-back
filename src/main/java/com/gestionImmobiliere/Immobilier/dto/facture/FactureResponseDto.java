package com.gestionImmobiliere.Immobilier.dto.facture;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FactureResponseDto {
    private long idQuitance;
    private double montantPaye;
    private String slug;
    private String slugOrganisme;
    private long idOrganisme;
    private long idContrat;
    private long numero;
    private double montantRestant;
}
