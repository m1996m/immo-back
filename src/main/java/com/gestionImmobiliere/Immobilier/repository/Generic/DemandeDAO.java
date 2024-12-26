package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Demande;
import org.springframework.stereotype.Repository;

@Repository
public class DemandeDAO extends GenericDAO<Demande> {
    public DemandeDAO() {
        super(Demande.class);
    }
}
