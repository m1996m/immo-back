package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.quitance.QuitanceDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Quitance;
import com.gestionImmobiliere.Immobilier.service.QuitanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quitance")
public class QuitanceController extends GenericController<Quitance, QuitanceDto> {
    private final QuitanceService quitanceService;

    public QuitanceController(
            GenericService<Quitance> genericService,
            GenericDtoMapper<QuitanceDto, Quitance> genericDtoMapper,
            QuitanceService quitanceService
    ) {
        super(genericService, genericDtoMapper);
        this.quitanceService = quitanceService;
    }

    @GetMapping("/user")
    public List<Quitance> getListQuitanceByIdUser(
            @RequestParam("idUser") long idUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return quitanceService.findAllByAttributeNameAndValue(idUser,"contrat.user.idUser", page, size, slugOrganisme);
    }
}
