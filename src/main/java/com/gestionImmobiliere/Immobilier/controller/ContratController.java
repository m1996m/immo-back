package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.contrat.ContratDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.service.ContratService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contrat")
public class ContratController extends GenericController<Contrat, ContratDto> {
    private final ContratService contratService;

    public ContratController(
            GenericService<Contrat> genericService,
            GenericDtoMapper<ContratDto, Contrat> genericDtoMapper,
            ContratService contratService
    ) {
        super(genericService, genericDtoMapper);
        this.contratService = contratService;
    }

    @GetMapping("/type")
    public List<Contrat> getListContratByType(
            @RequestParam("type") long type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return contratService.findAllByAttributeNameAndValue(type,"type", page, size, slugOrganisme);
    }

    @GetMapping("/bien")
    public List<Contrat> getListContratByBien(
            @RequestParam("idBien") long idBien,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return contratService.findAllByAttributeNameAndValue(idBien,"bien.idBien", page, size, slugOrganisme);
    }
}
