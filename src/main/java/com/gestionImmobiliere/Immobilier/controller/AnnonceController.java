package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.annonce.AnnonceDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Annonce;
import com.gestionImmobiliere.Immobilier.service.AnnonceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/annonce")
public class AnnonceController extends GenericController<Annonce, AnnonceDto> {
    private final AnnonceService annonceService;

    public AnnonceController(
            GenericService<Annonce> genericService,
            GenericDtoMapper<AnnonceDto, Annonce> genericDtoMapper,
            AnnonceService annonceService
    ) {
        super(genericService, genericDtoMapper);
        this.annonceService = annonceService;
    }

    @GetMapping("/type")
    public List<Annonce> getListAnnonceByType(
            @RequestParam("type") long type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return annonceService.findAllByAttributeNameAndValue(type,"type", page, size, slugOrganisme);
    }

    @GetMapping("/between/date")
    public List<Annonce> getListBetweenByDate(
            @RequestParam("dateDebut") Date dateDebut,
            @RequestParam("dateFin") Date dateFin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam("slugOrganisme") String slugOrganisme
    ){
        return annonceService.findAttributeNameBetwennTwoDate("datePublication", dateDebut, dateFin, page, size, slugOrganisme);
    }
}
