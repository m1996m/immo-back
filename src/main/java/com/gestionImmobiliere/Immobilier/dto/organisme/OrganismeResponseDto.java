package com.gestionImmobiliere.Immobilier.dto.organisme;

import com.gestionImmobiliere.Immobilier.dto.annonce.AnnonceDto;
import com.gestionImmobiliere.Immobilier.dto.bien.BienDto;
import com.gestionImmobiliere.Immobilier.dto.clauseContrat.ClauseContratDto;
import com.gestionImmobiliere.Immobilier.dto.declaration.DeclarationDto;
import com.gestionImmobiliere.Immobilier.dto.demande.DemandeDto;
import com.gestionImmobiliere.Immobilier.dto.echange.EchangeDto;
import com.gestionImmobiliere.Immobilier.dto.user.UserResponseDto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrganismeResponseDto {
    private long idOrganisme;
    private String email;
    private String name;
    private String address;
    private String type;
    private String tel;
    private String slug;
    private String image;
    private LocalDateTime createdAt;
    private long idUser;
    private List<UserResponseDto> users;
    private List<BienDto> biens;
    private List<ClauseContratDto> clauseContrats;
    private List<EchangeDto> echanges;
    private List<DeclarationDto> declarations;
    private List<DemandeDto> demandes;
    private List<AnnonceDto> annonces;
}
