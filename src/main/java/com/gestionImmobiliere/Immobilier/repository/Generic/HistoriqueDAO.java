package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Historique;
import org.springframework.stereotype.Repository;

@Repository
public class HistoriqueDAO extends GenericDAO<Historique> {
    public HistoriqueDAO() {
        super(Historique.class);
    }
}
