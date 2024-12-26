package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Paiement;
import org.springframework.stereotype.Repository;

@Repository
public class PaiementDAO extends GenericDAO<Paiement> {
    public PaiementDAO() {
        super(Paiement.class);
    }
}
