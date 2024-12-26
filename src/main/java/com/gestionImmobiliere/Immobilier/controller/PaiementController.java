package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.paiement.PaiementDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Declaration;
import com.gestionImmobiliere.Immobilier.model.Paiement;
import com.gestionImmobiliere.Immobilier.service.PaiementService;
import org.springframework.web.bind.annotation.*;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/paiement")
public class PaiementController extends GenericController<Paiement, PaiementDto> {
    private final PaiementService paymentService;

    public PaiementController(
            GenericService<Paiement> genericService,
            GenericDtoMapper<PaiementDto, Paiement> genericDtoMapper,
            PaiementService paymentService
    ) {
        super(genericService, genericDtoMapper);
        this.paymentService = paymentService;
    }

    /**
     *
     * @param datePaiement
     * @param slugOrganisme
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/date")
    public List<Paiement> getListPaiementByDate(
            @RequestParam("datePaiement") Date datePaiement,
            @RequestParam("slugOrganisme") String slugOrganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        return paymentService.findAttributeNameBetwennTwoDate(
                "datePaiement",
                datePaiement,
                datePaiement,
                page,
                size,
                slugOrganisme
        );
    }
}
