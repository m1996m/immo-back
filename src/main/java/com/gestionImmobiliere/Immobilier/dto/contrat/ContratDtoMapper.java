package com.gestionImmobiliere.Immobilier.dto.contrat;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.model.ClauseContrat;
import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.BienService;
import com.gestionImmobiliere.Immobilier.service.ClauseContratService;
import com.gestionImmobiliere.Immobilier.service.ContratService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ContratDtoMapper implements GenericDtoMapper<ContratDto, Contrat> {
    private final ContratService contratService;
    private final UserService userService;
    private final BienService bienService;
    private final ClauseContratService clauseService;

    public ContratDtoMapper(
            ContratService contratService,
            UserService userService,
            BienService bienService,
            ClauseContratService clauseService
    ) {
        this.contratService = contratService;
        this.userService = userService;
        this.bienService = bienService;
        this.clauseService = clauseService;
    }

    @Override
    public ContratDto toDto(Contrat contrat) {
        return ContratDto.builder()
                .idContrat(contrat.getIdContrat())
                .dateContrat(contrat.getDateContrat())
                .dateFinContrat(contrat.getDateFinContrat())
                .slug(contrat.getSlug())
                .createdAt(contrat.getCreatedAt())
                .build();
    }

    @Override
    public Contrat toEntity(ContratDto dto) {
        Bien bien = bienService.findUniqueById(dto.getIdBien());
        ClauseContrat clause = clauseService.findUniqueById(dto.getIdClauseContrat());

        if (dto.getSlug() != null) {
            Contrat contrat = contratService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, bien, clause, contrat);
        }else {
            User user = userService.findUniqueById(dto.getIdUser());

            dto.setSlugOrganisme(bien.getSlugOrganisme());

            return dto.create(dto, user, bien, clause);
        }
    }
}
