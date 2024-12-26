package com.gestionImmobiliere.Immobilier.dto.annonce;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Annonce;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.service.AnnonceService;
import com.gestionImmobiliere.Immobilier.service.BienService;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import org.springframework.stereotype.Component;

@Component
public class AnnonceDtoMapper implements GenericDtoMapper<AnnonceDto, Annonce> {
    private final OrganismeService organismeService;
    private final AnnonceService annonceService;
    private final BienService bienService;

    public AnnonceDtoMapper(
            OrganismeService organismeService,
            AnnonceService annonceService,
            BienService bienService
    ) {
        this.organismeService = organismeService;
        this.annonceService = annonceService;
        this.bienService = bienService;
    }

    @Override
    public AnnonceDto toDto(Annonce annonce) {
        return AnnonceDto.builder()
                .idAnnonce(annonce.getIdAnnonce())
                .slug(annonce.getSlug())
                .titre(annonce.getTitre())
                .type(annonce.getType())
                .description(annonce.getDescription())
                .createdAt(annonce.getCreatedAt())
                .build();
    }

    @Override
    public Annonce toEntity(AnnonceDto dto) {
        if (dto.getSlug() != null) {
            Annonce annonce = annonceService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, annonce);
        }else {
            Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());
            Bien bien = bienService.findUniqueById(dto.getIdBien());

            dto.setSlugOrganisme(organisme.getSlug());

            return dto.create(dto, bien, organisme);
        }
    }
}
