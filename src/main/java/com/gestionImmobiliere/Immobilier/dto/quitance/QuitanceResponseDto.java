package com.gestionImmobiliere.Immobilier.dto.quitance;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuitanceResponseDto {
    private long idQuitance;
    private Date datePaiement;
    private double montantPaye;
    private String slug;
    private String slugOrganisme;
    private long idOrganisme;
    private long idContrat;
    private String periode;
    private long numero;
}
