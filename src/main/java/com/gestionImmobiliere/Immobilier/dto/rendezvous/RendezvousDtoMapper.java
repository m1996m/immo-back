package com.gestionImmobiliere.Immobilier.dto.rendezvous;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Rendezvous;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.RendezvousService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class RendezvousDtoMapper implements GenericDtoMapper<RendezvousDto, Rendezvous> {
    private final RendezvousService rendezvousService;
    private final UserService userService;

    public RendezvousDtoMapper(RendezvousService rendezvousService, UserService userService) {
        this.rendezvousService = rendezvousService;
        this.userService = userService;
    }

    @Override
    public RendezvousDto toDto(Rendezvous rendezvous) {
        return RendezvousDto.builder()
                .idRendezvous(rendezvous.getIdRendezvous())
                .slug(rendezvous.getSlug())
                .heure(rendezvous.getHeure())
                .dateRendezvous(rendezvous.getDateRendezvous())
                .build();
    }

    @Override
    public Rendezvous toEntity(RendezvousDto rendezvousDto) {
        if (rendezvousDto.getSlug() != null) {
            Rendezvous rendezvous = rendezvousService.findUniqueWithValueAndAttribut(rendezvousDto.getSlug(), "slug");

            return rendezvousDto.update(rendezvousDto, rendezvous);
        }else {
            User user = userService.findUniqueById(rendezvousDto.getIdUser());

            rendezvousDto.setSlugOrganisme(user.getSlugOrganisme());

            return rendezvousDto.create(rendezvousDto, user);
        }
    }
}
