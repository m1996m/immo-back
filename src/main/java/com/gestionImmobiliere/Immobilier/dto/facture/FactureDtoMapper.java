package com.gestionImmobiliere.Immobilier.dto.facture;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.model.Sequence_table;
import com.gestionImmobiliere.Immobilier.service.ContratService;
import com.gestionImmobiliere.Immobilier.service.FactureService;
import com.gestionImmobiliere.Immobilier.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class FactureDtoMapper implements GenericDtoMapper<FactureDto, Facture> {
    private final ContratService contratService;
    private final FactureService factureService;
    private final Sequence_tableService service;

    public FactureDtoMapper(
            ContratService contratService,
            FactureService factureService,
            Sequence_tableService service
    ) {
        this.contratService = contratService;
        this.factureService = factureService;
        this.service = service;
    }

    @Override
    public FactureDto toDto(Facture facture) {
        return FactureDto.builder()
                .idFacture(facture.getIdFacture())
                //.datePaiement(facture.getDatePaiement())
                .montantPaye(facture.getMontantPaye())
                .slug(facture.getSlug())
                .slugOrganisme(facture.getSlugOrganisme())
                .build();
    }

    @Override
    public Facture toEntity(FactureDto dto) {
        if (dto.getSlug() != null) {
            Facture facture = factureService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, facture);
        }else {
            Contrat contrat = contratService.findUniqueById(dto.getIdContrat());
            Sequence_table sequenceTable = service.find("facture");

            service.update(sequenceTable);

            dto.setSlugOrganisme(contrat.getSlugOrganisme());
            dto.setNumero(sequenceTable.getNext_value());

            return dto.create(dto, contrat);
        }
    }
}
