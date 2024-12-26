package com.gestionImmobiliere.Immobilier.dto.echange;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Echange;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.EchangeService;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class EchangeDtoMapper implements GenericDtoMapper<EchangeDto, Echange> {
    private final EchangeService echangeService;
    private final UserService userService;
    private final OrganismeService organismeService;

    public EchangeDtoMapper(
            EchangeService echangeService,
            UserService userService,
            OrganismeService organismeService
    ) {
        this.echangeService = echangeService;
        this.userService = userService;
        this.organismeService = organismeService;
    }

    @Override
    public EchangeDto toDto(Echange echange) {
        return EchangeDto.builder()
                .commentaire(echange.getCommentaire())
                .idEchange(echange.getIdEchange())
                .slug(echange.getSlug())
                .build();
    }

    @Override
    public Echange toEntity(EchangeDto dto) {
        if (dto.getSlug() != null) {
            Echange echange = echangeService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, echange);
        }else {
            User user = userService.findUniqueById(dto.getIdUser());
            Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());

            dto.setSlugOrganisme(organisme.getSlug());

            return dto.create(dto, user, organisme);
        }
    }
}
