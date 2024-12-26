package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Avis;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class AvisDAO extends GenericDAO<Avis> {
    public AvisDAO() {
        super(Avis.class);
    }
}
