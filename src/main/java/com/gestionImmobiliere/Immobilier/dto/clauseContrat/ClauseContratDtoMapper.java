package com.gestionImmobiliere.Immobilier.dto.clauseContrat;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.ClauseContrat;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.ClauseContratService;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ClauseContratDtoMapper implements GenericDtoMapper<ClauseContratDto, ClauseContrat> {
    private final ClauseContratService clauseContratService;
    private final OrganismeService organismeService;
    private final UserService userService;

    public ClauseContratDtoMapper(
            ClauseContratService clauseContratService,
            OrganismeService organismeService,
            UserService userService
    ) {
        this.clauseContratService = clauseContratService;
        this.organismeService = organismeService;
        this.userService = userService;
    }

    @Override
    public ClauseContratDto toDto(ClauseContrat dto) {
        return ClauseContratDto.builder()
                .idClauseContrat(dto.getIdClauseContrat())
                .slug(dto.getSlug())
                .commentaire(dto.getCommentaire())
                .type(dto.getType())
                .slugOrganisme(dto.getSlugOrganisme())
                .build();
    }

    @Override
    public ClauseContrat toEntity(ClauseContratDto dto) {
        if (dto.getSlug() != null){
            ClauseContrat clauseContrat = clauseContratService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, clauseContrat);
        }else{
            Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());
            User user = userService.findUniqueById(dto.getIdUser());

            dto.setSlugOrganisme(organisme.getSlug());

            return dto.create(dto, organisme, user);
        }
    }
}
