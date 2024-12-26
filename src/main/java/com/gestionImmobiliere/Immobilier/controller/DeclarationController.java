package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.declaration.DeclarationDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Declaration;
import com.gestionImmobiliere.Immobilier.service.DeclarationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/declaration")
public class DeclarationController extends GenericController<Declaration, DeclarationDto> {
    private final DeclarationService declarationService;

    public DeclarationController(
            GenericService<Declaration> genericService,
            GenericDtoMapper<DeclarationDto, Declaration> genericDtoMapper,
            DeclarationService declarationService
    ) {
        super(genericService, genericDtoMapper);
        this.declarationService = declarationService;
    }

    /**
     *
     * @param statut
     * @param slugOrganisme
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/statut")
    public List<Declaration> getListDeclarationByStatut(
            @RequestParam("statut") String statut,
            @RequestParam("slugOrganisme") String slugOrganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ){
        return declarationService.findAllByAttributeNameAndValue(statut,"statut", page, size, slugOrganisme);
    }
}
