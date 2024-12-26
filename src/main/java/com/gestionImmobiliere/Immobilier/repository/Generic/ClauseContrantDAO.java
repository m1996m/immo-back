package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.ClauseContrat;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class ClauseContrantDAO extends GenericDAO<ClauseContrat> {
    public ClauseContrantDAO() {
        super(ClauseContrat.class);
    }
}
