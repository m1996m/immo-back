package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Statistique;
import org.springframework.stereotype.Repository;

@Repository
public class StatistiqueDAO extends GenericDAO<Statistique> {
    public StatistiqueDAO() {
        super(Statistique.class);
    }
}
