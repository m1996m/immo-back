package com.gestionImmobiliere.Immobilier.dto.declaration;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Declaration;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.DeclarationService;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeclarationDtoMapper implements GenericDtoMapper<DeclarationDto, Declaration> {
    private final DeclarationService declarationService;
    private final OrganismeService organismeService;
    private final UserService userService;

    public DeclarationDtoMapper(
            DeclarationService declarationService,
            OrganismeService organismeService,
            UserService userService
    ) {
        this.declarationService = declarationService;
        this.organismeService = organismeService;
        this.userService = userService;
    }

    @Override
    public DeclarationDto toDto(Declaration declaration) {
        return DeclarationDto.builder()
                .idDeclaration(declaration.getIdDeclaration())
                .raison(declaration.getRaison())
                .build();
    }

    @Override
    public Declaration toEntity(DeclarationDto dto) {
        if (dto.getSlug() != null){
            Declaration declaration = declarationService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, declaration);
        }else {
            Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());
            User user = userService.findUniqueById(dto.getIdOrganisme());

            dto.setSlugOrganisme(organisme.getSlug());

            return dto.create(dto,user, organisme);
        }
    }
}
