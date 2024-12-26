package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.clauseContrat.ClauseContratDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.ClauseContrat;
import com.gestionImmobiliere.Immobilier.service.ClauseContratService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clause/contrat")
public class ClauseContratController extends GenericController<ClauseContrat, ClauseContratDto> {
    private final ClauseContratService clauseContratService;

    public ClauseContratController(
            GenericService<ClauseContrat> genericService,
            GenericDtoMapper<ClauseContratDto, ClauseContrat> genericDtoMapper,
            ClauseContratService clauseContratService
    ) {
        super(genericService, genericDtoMapper);
        this.clauseContratService = clauseContratService;
    }

    @GetMapping("/organisme/type")
    public List<ClauseContrat> getListAvisByIdOrganismeAndType(
            @RequestParam("type") long type,
            @RequestParam("idOrganisme") long idOrganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return clauseContratService.findListAndTwoValue(
                type,
                "type",
                idOrganisme,
                "organisme.idOrganisme",
                page,
                size,
                slugOrganisme
        );
    }
}
