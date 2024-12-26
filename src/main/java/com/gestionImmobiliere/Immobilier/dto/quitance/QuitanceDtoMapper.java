package com.gestionImmobiliere.Immobilier.dto.quitance;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.Quitance;
import com.gestionImmobiliere.Immobilier.model.Sequence_table;
import com.gestionImmobiliere.Immobilier.service.ContratService;
import com.gestionImmobiliere.Immobilier.service.QuitanceService;
import com.gestionImmobiliere.Immobilier.service.Sequence_tableService;
import org.springframework.stereotype.Component;

@Component
public class QuitanceDtoMapper implements GenericDtoMapper<QuitanceDto, Quitance> {
    private final ContratService contratService;
    private final QuitanceService quitanceService;
    private final Sequence_tableService service;

    public QuitanceDtoMapper(
            ContratService contratService,
            QuitanceService quitanceService,
            Sequence_tableService service
    ) {
        this.contratService = contratService;
        this.quitanceService = quitanceService;
        this.service = service;
    }

    @Override
    public QuitanceDto toDto(Quitance quitance) {
        return QuitanceDto.builder()
                .idQuitance(quitance.getIdQuitance())
                .datePaiement(quitance.getDatePaiement())
                .montantPaye(quitance.getMontantPaye())
                .slug(quitance.getSlug())
                .slugOrganisme(quitance.getSlugOrganisme())
                .build();
    }

    @Override
    public Quitance toEntity(QuitanceDto dto) {
        if (dto.getSlug() != null) {
            Quitance quitance = quitanceService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, quitance);
        }else {
            Contrat contrat = contratService.findUniqueById(dto.getIdContrat());
            Sequence_table sequenceTable = service.find("quitance");

            service.update(sequenceTable);

            dto.setSlugOrganisme(contrat.getSlugOrganisme());
            dto.setNumero(sequenceTable.getNext_value());

            return dto.create(dto, contrat);
        }
    }
}
