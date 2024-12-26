package com.gestionImmobiliere.Immobilier.dto.bien;

import com.gestionImmobiliere.Immobilier.dto.annonce.AnnonceDto;
import com.gestionImmobiliere.Immobilier.dto.avis.AvisDto;
import com.gestionImmobiliere.Immobilier.dto.contrat.ContratDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BienResponseDto {
    private long idBien;
    private String type;
    private String adresse;
    private String ville;
    private String slugOrganisme;
    private String surface;
    private int nombrePiece;
    private String description;
    private double prix;
    private String slug;
    private long idOrganisme;
    private LocalDateTime createdAt;
    private List<AvisDto> avis;
    private List<ContratDto> contrats;
    private List<AnnonceDto> annonces;
}
