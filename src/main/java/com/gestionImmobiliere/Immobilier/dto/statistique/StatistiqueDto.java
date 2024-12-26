package com.gestionImmobiliere.Immobilier.dto.statistique;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatistiqueDto {
    private Long idStatistiques;
    private int nombreAnnonces;
    private double revenusGeneres;
    private int contratsSignes;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;

    public Statistique create(StatistiqueDto dto, Organisme organisme){
        Statistique statistique = new Statistique();
        statistique.setRevenusGeneres(dto.getRevenusGeneres());
        statistique.setNombreAnnonces(dto.getNombreAnnonces());
        statistique.setContratsSignes(dto.getContratsSignes());
        statistique.setSlugOrganisme(dto.getSlugOrganisme());
        statistique.setOrganisme(organisme);

        return statistique;
   }

    public Statistique update(StatistiqueDto dto, Statistique statistique){
        statistique.setRevenusGeneres(dto.getRevenusGeneres());
        statistique.setNombreAnnonces(dto.getNombreAnnonces());
        statistique.setContratsSignes(dto.getContratsSignes());

        return statistique;
    }
}
