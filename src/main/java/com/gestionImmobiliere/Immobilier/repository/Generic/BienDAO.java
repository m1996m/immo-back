package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class BienDAO extends GenericDAO<Bien> {
    public BienDAO() {
        super(Bien.class);
    }
}
