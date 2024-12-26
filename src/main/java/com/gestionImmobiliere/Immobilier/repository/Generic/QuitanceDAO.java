package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Quitance;
import org.springframework.stereotype.Repository;

@Repository
public class QuitanceDAO extends GenericDAO<Quitance> {
    public QuitanceDAO() {
        super(Quitance.class);
    }
}
