package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Declaration;
import org.springframework.stereotype.Repository;

@Repository
public class DeclarationDAO extends GenericDAO<Declaration> {
    public DeclarationDAO() {
        super(Declaration.class);
    }
}
