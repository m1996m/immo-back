package com.gestionImmobiliere.Immobilier.dto.avis;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvisResponseDto {
    private long idAvis;
    private String commentaire;
    private int note;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idBien;
    private long idUser;
}
