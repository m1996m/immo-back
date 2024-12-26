package com.gestionImmobiliere.Immobilier.dto.echange;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EchangeResponseDto {
    private long idAvis;
    private String commentaire;
    private String slugOrganisme;
    private long idUser;
    private long idOrganisme;
}
