package com.gestionImmobiliere.Immobilier.dto.demande;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Annonce;
import com.gestionImmobiliere.Immobilier.model.Demande;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import com.gestionImmobiliere.Immobilier.service.AnnonceService;
import com.gestionImmobiliere.Immobilier.service.DemandeService;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DemandeDtoMapper implements GenericDtoMapper<DemandeDto, Demande> {
    private final DemandeService demandeService;
    private final OrganismeService organismeService;
    private final UserService userService;
    private final AnnonceService annonceService;

    public DemandeDtoMapper(
            DemandeService demandeService,
            OrganismeService organismeService,
            UserService userService,
            AnnonceService annonceService
    ) {
        this.demandeService = demandeService;
        this.organismeService = organismeService;
        this.userService = userService;
        this.annonceService = annonceService;
    }

    @Override
    public DemandeDto toDto(Demande demande) {
        return DemandeDto.builder()
                .idDemande(demande.getIdDeamande())
                .dateDemande(demande.getDateDemande())
                .slug(demande.getSlug())
                .type(demande.getType())
                .statut(demande.getStatut())
                .build();
    }

    @Override
    public Demande toEntity(DemandeDto dto) {
        if (dto.getSlug() != null) {
            Demande demande = demandeService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, demande);
        }else {
            Organisme organisme = organismeService.findUniqueById(dto.getIdOrganisme());
            Annonce annonce = annonceService.findUniqueById(dto.getIdAnnonce());
            User user = userService.findUniqueById(dto.getIdUser());

            dto.setSlugOrganisme(organisme.getSlug());

            return dto.create(dto, user, organisme, annonce);
        }
    }
}
