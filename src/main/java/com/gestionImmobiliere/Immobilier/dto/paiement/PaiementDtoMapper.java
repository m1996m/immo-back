package com.gestionImmobiliere.Immobilier.dto.paiement;

import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.model.Paiement;
import com.gestionImmobiliere.Immobilier.service.FactureService;
import com.gestionImmobiliere.Immobilier.service.PaiementService;
import org.springframework.stereotype.Component;

@Component
public class PaiementDtoMapper implements GenericDtoMapper<PaiementDto, Paiement> {
    private final PaiementService paiementService;
    private final FactureService factureService;

    public PaiementDtoMapper(PaiementService paiementService, FactureService factureService) {
        this.paiementService = paiementService;
        this.factureService = factureService;
    }

    @Override
    public PaiementDto toDto(Paiement paiement) {
        return PaiementDto.builder()
                .idPaiement(paiement.getIdPaiement())
                .modePaiement(paiement.getModePaiement())
                .montant(paiement.getMontant())
                .datePaiement(paiement.getDatePaiement())
                .build();
    }

    @Override
    public Paiement toEntity(PaiementDto dto) {
        if (dto.getSlug() != null){
            Paiement paiement = paiementService.findUniqueWithValueAndAttribut(dto.getSlug(), "slug");

            return dto.update(dto, paiement);
        }else {
            Facture facture = factureService.findUniqueById(dto.getIdFacture());

            dto.setSlugOrganisme(facture.getSlugOrganisme());

            return dto.create(dto, facture);
        }
    }
}
