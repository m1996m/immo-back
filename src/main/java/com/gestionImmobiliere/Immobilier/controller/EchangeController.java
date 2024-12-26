package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.echange.EchangeDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Echange;
import com.gestionImmobiliere.Immobilier.service.EchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/echange")
public class EchangeController extends GenericController<Echange, EchangeDto> {
    private final EchangeService echangeService;

    public EchangeController(
            GenericService<Echange> genericService,
            GenericDtoMapper<EchangeDto, Echange> genericDtoMapper,
            EchangeService echangeService
    ) {
        super(genericService, genericDtoMapper);
        this.echangeService = echangeService;
    }

    @GetMapping("/bien")
    public List<Echange> getListAvisByIdBien(
            @RequestParam("idOrganisme") long idOrganisme,
            @RequestParam("idUser") long idUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return echangeService.findListOrTwoValue(
                idOrganisme,
                "organisme.idOrganisme",
                idUser,
                "user.idUser",
                page,
                size,
                slugOrganisme
        );
    }
}
