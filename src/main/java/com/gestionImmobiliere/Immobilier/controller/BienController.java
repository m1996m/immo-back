package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.bien.BienDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.service.BienService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bien")
public class BienController extends GenericController<Bien, BienDto> {
    private final BienService bienService;

    public BienController(
            GenericService<Bien> genericService,
            GenericDtoMapper<BienDto, Bien> genericDtoMapper,
            BienService bienService
    ) {
        super(genericService, genericDtoMapper);
        this.bienService = bienService;
    }

    @GetMapping("/type")
    public List<Bien> getListBienByType(
            @RequestParam("type") String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return bienService.findAllByAttributeNameAndValue(type, "type", page, size, slugOrganisme);
    }

    @GetMapping("/ville")
    public List<Bien> getListBienByVille(
            @RequestParam("ville") String ville,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return bienService.findAllByAttributeNameAndValue(ville, "ville", page, size, slugOrganisme);
    }

    @GetMapping("/organisme")
    public List<Bien> getListBienByOrganisme(
            @RequestParam("idOraganisme") long idOraganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return bienService.findDynamicWithJoinOneTable("organisme", "idOrganisme", idOraganisme, page, size, slugOrganisme);
    }

    @GetMapping("/ville/type")
    public List<Bien> getListBienByVilleAndType(
            @RequestParam("ville") String ville,
            @RequestParam("type") String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return bienService.findListAndTwoValue(ville, "ville", type,"type", page, size, slugOrganisme);
    }

    @GetMapping("/statut")
    public List<Bien> getListBienByStatut(
            @RequestParam("statut") long statut,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return bienService.findAllByAttributeNameAndValue(statut, "statut", page, size, slugOrganisme);
    }

    @GetMapping("/organisme/ville/type")
    public List<Bien> getListBienByOrganismeAndVilleAndType(
            @RequestParam("ville") String ville,
            @RequestParam("type") String type,
            @RequestParam("idOrganisme") String idOrganisme,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return bienService.findListAndThreeValue(
                "ville",
                "type",
                "organisme.idOrganisme",
                ville,
                type,
                idOrganisme,
                page,
                size,
                slugOrganisme
        );
    }
}
