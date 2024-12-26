package com.gestionImmobiliere.Immobilier.dto.avis;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Avis;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.AvisService;
import com.gestionImmobiliere.Immobilier.service.BienService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AvisDtoMapper implements GenericDtoMapper<AvisDto, Avis> {
    private final BienService bienService;
    private final UserService userService;
    private final AvisService avisService;

    public AvisDtoMapper(BienService bienService, UserService userService, AvisService avisService) {
        this.bienService = bienService;
        this.userService = userService;
        this.avisService = avisService;
    }

    @Override
    public AvisDto toDto(Avis avis) {
        return AvisDto.builder()
                .idAvis(avis.getIdAvis())
                .slug(avis.getSlug())
                .commentaire(avis.getCommentaire())
                .note(avis.getNote())
                .build();
    }

    @Override
    public Avis toEntity(AvisDto avisDto) {
        if (avisDto.getSlug() != null) {
            Avis avis = avisService.findUniqueWithValueAndAttribut(avisDto.getSlug(), "slug");

            return avisDto.update(avisDto, avis);
        }else {
            Bien bien = bienService.findUniqueById(avisDto.getIdBien());
            User user = userService.findUniqueById(avisDto.getIdUser());

            avisDto.setSlugOrganisme(bien.getSlugOrganisme());

            return avisDto.create(avisDto, bien, user);
        }
    }
}
