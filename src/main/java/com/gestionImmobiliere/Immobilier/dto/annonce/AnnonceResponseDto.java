package com.gestionImmobiliere.Immobilier.dto.annonce;

import com.gestionImmobiliere.Immobilier.dto.demande.DemandeDto;
import com.gestionImmobiliere.Immobilier.dto.echange.EchangeDto;
import com.gestionImmobiliere.Immobilier.dto.rendezvous.RendezvousDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnnonceResponseDto {
    private long idAnnonce;
    private Date datePublication;
    private String titre;
    private String description;
    private String type;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idBien;
    private long idOrganisme;
    private List<RendezvousDto> rendezvous;
    private List<EchangeDto> echanges;
    private List<DemandeDto> demandes;
}
