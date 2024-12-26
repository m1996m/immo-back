package com.gestionImmobiliere.Immobilier.dto.statistique;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatistiqueResponseDto {
    private Long idStatistiques;
    private int nombreAnnonces;
    private double revenusGeneres;
    private int contratsSignes;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
}
