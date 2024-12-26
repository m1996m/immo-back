package com.gestionImmobiliere.Immobilier.dto.bien;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.service.BienService;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import org.springframework.stereotype.Component;

@Component
public class BienDtoMapper implements GenericDtoMapper<BienDto, Bien> {
    private final BienService bienService;
    private final OrganismeService organismeService;

    public BienDtoMapper(BienService service, OrganismeService organismeService) {
        this.bienService = service;
        this.organismeService = organismeService;
    }

    @Override
    public BienDto toDto(Bien bien) {
        return BienDto.builder()
                .idBien(bien.getIdBien())
                .prix(bien.getPrix())
                .ville(bien.getVille())
                .type(bien.getType())
                .adresse(bien.getAdresse())
                //.statut(bien.getStatut())
                .build();
    }

    @Override
    public Bien toEntity(BienDto bienDto) {
        if (bienDto.getSlug() != null){
           Bien bien = bienService.findUniqueWithValueAndAttribut(bienDto.getSlug(), "slug");

           return bienDto.update(bienDto, bien);
        }else{
            Organisme organisme = organismeService.findUniqueById(bienDto.getIdOrganisme());

            bienDto.setSlugOrganisme(organisme.getSlug());

            return bienDto.create(bienDto, organisme);
        }
    }
}
