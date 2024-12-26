package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.organisme.OrganismeDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.service.OrganismeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organisme")
public class OrganismeController extends GenericController<Organisme, OrganismeDto> {

    public OrganismeController(
            GenericService<Organisme> genericService,
            GenericDtoMapper<OrganismeDto, Organisme> genericDtoMapper
    ) {
        super(genericService, genericDtoMapper);
    }
}
