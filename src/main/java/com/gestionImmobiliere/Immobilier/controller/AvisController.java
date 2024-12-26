package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.avis.AvisDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Avis;
import com.gestionImmobiliere.Immobilier.service.AvisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController extends GenericController<Avis, AvisDto> {
    private final AvisService avisService;

    public AvisController(
            GenericService<Avis> genericService,
            GenericDtoMapper<AvisDto, Avis> genericDtoMapper,
            AvisService avisService
    ) {
        super(genericService, genericDtoMapper);
        this.avisService = avisService;
    }

    @GetMapping("/bien")
    public List<Avis> getListAvisByIdBien(
            @RequestParam("idBien") long idBien,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return avisService.findDynamicWithJoinOneTable("bien", "idBien", idBien, page, size, slugOrganisme);
    }
}
