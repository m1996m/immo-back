package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.facture.FactureDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.service.FactureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/facture")
public class FactureController extends GenericController<Facture, FactureDto> {
    private final FactureService factureService;

    public FactureController(
            GenericService<Facture> genericService,
            GenericDtoMapper<FactureDto, Facture> genericDtoMapper,
            FactureService factureService
    ) {
        super(genericService, genericDtoMapper);
        this.factureService = factureService;
    }

    @GetMapping("/user")
    public List<Facture> getListFactureByIdUser(
            @RequestParam("idUser") long idUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return factureService.findAllByAttributeNameAndValue(idUser,"contrat.user.idUser", page, size, slugOrganisme);
    }
}
