package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.declaration.DeclarationDto;
import com.gestionImmobiliere.Immobilier.dto.fraisAgence.FraisAgenceDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.FraisAgence;
import com.gestionImmobiliere.Immobilier.service.FraisAgenceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/frais/agence")
public class FraisAgenceController extends GenericController<FraisAgence, FraisAgenceDto> {

    public FraisAgenceController(
            GenericService<FraisAgence> genericService,
            GenericDtoMapper<FraisAgenceDto, FraisAgence> genericDtoMapper
    ) {
        super(genericService, genericDtoMapper);
    }
}
