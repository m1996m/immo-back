package com.gestionImmobiliere.Immobilier.dto.user;

import com.gestionImmobiliere.Immobilier.dto.avis.AvisDto;
import com.gestionImmobiliere.Immobilier.dto.clauseContrat.ClauseContratDto;
import com.gestionImmobiliere.Immobilier.dto.contrat.ContratDto;
import com.gestionImmobiliere.Immobilier.dto.declaration.DeclarationDto;
import com.gestionImmobiliere.Immobilier.dto.demande.DemandeDto;
import com.gestionImmobiliere.Immobilier.dto.echange.EchangeDto;
import com.gestionImmobiliere.Immobilier.dto.rendezvous.RendezvousDto;
import com.gestionImmobiliere.Immobilier.enumeration.Genre;

import com.gestionImmobiliere.Immobilier.model.Historique;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private long idUser;
    private String email;
    private String firstName;
    private String tel;
    private String address;
    private String lastName;
    private String slug;
    private String avatar;
    private Genre gender = Genre.AUTRE;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private List<AvisDto> avis;
    private List<ContratDto> contrats;
    private List<ClauseContratDto> clauseContrats;
    private List<EchangeDto> echanges;
    private List<DeclarationDto> declarations;
    private List<RendezvousDto> rendezvous;
    private List<DemandeDto> Demande;
}
