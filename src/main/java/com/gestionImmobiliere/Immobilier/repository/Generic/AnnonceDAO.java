package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Annonce;
import com.gestionImmobiliere.Immobilier.model.Avis;
import org.springframework.stereotype.Repository;

@Repository
public class AnnonceDAO extends GenericDAO<Annonce> {
    public AnnonceDAO() {
        super(Annonce.class);
    }
}
