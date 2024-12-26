package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class FactureDAO extends GenericDAO<Facture> {
    public FactureDAO() {
        super(Facture.class);
    }
}
