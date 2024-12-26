package com.gestionImmobiliere.Immobilier.dto.statistique;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.Statistique;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import com.gestionImmobiliere.Immobilier.service.StatistiqueService;
import org.springframework.stereotype.Component;

@Component
public class StatistiqueDtoMapper implements GenericDtoMapper<StatistiqueDto, Statistique> {
    private final StatistiqueService service;
    private final OrganismeService organismeService;

    public StatistiqueDtoMapper(StatistiqueService service, OrganismeService organismeService) {
        this.service = service;
        this.organismeService = organismeService;
    }

    @Override
    public StatistiqueDto toDto(Statistique statistique) {

        return StatistiqueDto.builder()
                .idStatistiques(statistique.getIdStatistiques())
                .slug(statistique.getSlug())
                .nombreAnnonces(statistique.getNombreAnnonces())
                .contratsSignes(statistique.getContratsSignes())
                .revenusGeneres(statistique.getRevenusGeneres())
                .build();
    }

    @Override
    public Statistique toEntity(StatistiqueDto dto) {
        if (dto.getSlug() != null) {
            Statistique statistique = service.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, statistique);
        }else{
            Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());

            dto.setSlugOrganisme(organisme.getSlug());

            return dto.create(dto, organisme);
        }
    }
}
