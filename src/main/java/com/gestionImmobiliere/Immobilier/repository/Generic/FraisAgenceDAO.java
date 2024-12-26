package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.FraisAgence;
import org.springframework.stereotype.Repository;

@Repository
public class FraisAgenceDAO extends GenericDAO<FraisAgence> {
    public FraisAgenceDAO() {
        super(FraisAgence.class);
    }
}
