package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Statistique;
import com.gestionImmobiliere.Immobilier.repository.Generic.StatistiqueDAO;
import org.springframework.stereotype.Service;

@Service
public class StatistiqueService extends GenericService<Statistique> {
    public StatistiqueService(StatistiqueDAO statistiqueDAO) {
        super(statistiqueDAO);
    }
}
