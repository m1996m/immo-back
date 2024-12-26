package com.gestionImmobiliere.Immobilier.dto.organisme;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import org.springframework.stereotype.Component;

@Component
public class OrganismeDtoMapper implements GenericDtoMapper<OrganismeDto, Organisme> {
    private final OrganismeService organismeService;

    public OrganismeDtoMapper(OrganismeService organismeService) {
        this.organismeService = organismeService;
    }

    @Override
    public OrganismeDto toDto(Organisme organisme) {
        return OrganismeDto.builder()
                .name(organisme.getName())
                .idOrganisme(organisme.getIdOrganisme())
                .address(organisme.getAddress())
                .email(organisme.getEmail())
                .tel(organisme.getTel())
                .type(organisme.getType())
                .image(organisme.getImage())
                .build();
    }

    @Override
    public Organisme toEntity(OrganismeDto organismeDto) {
        if (organismeDto.getSlug() != null){
            Organisme organisme = organismeService.findUniqueWithValueAndAttribut(
                    organismeDto.getSlug(),
                    "slug"
            );

            return organismeDto.update(organismeDto, organisme);
        }else{
            return organismeDto.create(organismeDto);
        }
    }
}
