package com.gestionImmobiliere.Immobilier.controller;

import com.gestionImmobiliere.Immobilier.dto.statistique.StatistiqueDto;
import com.gestionImmobiliere.Immobilier.generic.GenericController;
import com.gestionImmobiliere.Immobilier.generic.GenericDtoMapper;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Statistique;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistique")
public class StatistiqueController extends GenericController<Statistique, StatistiqueDto> {
    public StatistiqueController(
            GenericService<Statistique> genericService,
            GenericDtoMapper<StatistiqueDto, Statistique> genericDtoMapper
    ) {
        super(genericService, genericDtoMapper);
    }
}
