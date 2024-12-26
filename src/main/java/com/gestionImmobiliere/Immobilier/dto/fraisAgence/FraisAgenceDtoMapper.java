package com.gestionImmobiliere.Immobilier.dto.fraisAgence;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.FraisAgence;
import com.gestionImmobiliere.Immobilier.service.ContratService;
import com.gestionImmobiliere.Immobilier.service.FraisAgenceService;
import org.springframework.stereotype.Component;

@Component
public class FraisAgenceDtoMapper implements GenericDtoMapper<FraisAgenceDto, FraisAgence> {
    private final FraisAgenceService fraisAgenceService;
    private final ContratService contratService;

    public FraisAgenceDtoMapper(FraisAgenceService fraisAgenceService, ContratService contratService) {
        this.fraisAgenceService = fraisAgenceService;
        this.contratService = contratService;
    }

    @Override
    public FraisAgenceDto toDto(FraisAgence fraisAgence) {
        return FraisAgenceDto.builder()
                .idFraisAgence(fraisAgence.getIdFraisAgence())
                .type(fraisAgence.getType())
                .slug(fraisAgence.getSlug())
                .montant(fraisAgence.getMontant())
                .build();
    }

    @Override
    public FraisAgence toEntity(FraisAgenceDto dto) {
        if (dto.getSlug() != null) {
            FraisAgence fraisAgence = fraisAgenceService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, fraisAgence);
        }else {
            Contrat contrat = contratService.findUniqueById(dto.getIdContrat());

            dto.setSlugOrganisme(contrat.getSlugOrganisme());

            return dto.create(dto, contrat);
        }
    }
}
