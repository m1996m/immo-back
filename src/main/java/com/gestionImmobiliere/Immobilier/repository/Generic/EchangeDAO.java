package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Echange;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
public class EchangeDAO extends GenericDAO<Echange> {
    public EchangeDAO() {
        super(Echange.class);
    }
}
