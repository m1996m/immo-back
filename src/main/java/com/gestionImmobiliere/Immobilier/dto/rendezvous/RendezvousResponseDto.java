package com.gestionImmobiliere.Immobilier.dto.rendezvous;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RendezvousResponseDto {
    private long idRendezvous;
    private String statut;
    private Date dateRendezvous;
    private String heure;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idAnnonce;
    private long idUser;
}
