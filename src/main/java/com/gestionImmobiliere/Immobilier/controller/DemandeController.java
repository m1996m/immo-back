package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.demande.DemandeDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Demande;
import com.gestionImmobiliere.Immobilier.service.DemandeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/demande")
public class DemandeController extends GenericController<Demande, DemandeDto> {
    private final DemandeService demandeService;

    public DemandeController(
            GenericService<Demande> genericService,
            GenericDtoMapper<DemandeDto, Demande> genericDtoMapper,
            DemandeService demandeService
    ) {
        super(genericService, genericDtoMapper);
        this.demandeService = demandeService;
    }

    /**
     *
     * @param ville
     * @param slugOrganisme
     * @param statut
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/ville/statut")
    public List<Demande> getListDemandeByVilleAndStatut(
            @RequestParam("ville") String ville,
            @RequestParam("slugOrganisme") String slugOrganisme,
            @RequestParam("Statut") String statut,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        return demandeService.findListAndTwoValue(ville,"ville", statut,"statut", page, size, slugOrganisme);
    }

    /**
     *
     * @param type
     * @param slugOrganisme
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/type")
    public List<Demande> getListDemandeByType(
            @RequestParam("type") String type,
            @RequestParam("slugOrganisme") String slugOrganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        return demandeService.findAllByAttributeNameAndValue(type,"type", page, size, slugOrganisme);
    }
}
