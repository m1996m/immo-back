package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Contrat;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class ContratDAO extends GenericDAO<Contrat> {
    public ContratDAO() {
        super(Contrat.class);
    }
}
