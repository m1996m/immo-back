package com.gestionImmobiliere.Immobilier.repository.Generic;

import com.gestionImmobiliere.Immobilier.generic.GenericDAO;
import com.gestionImmobiliere.Immobilier.model.Avis;
import com.gestionImmobiliere.Immobilier.model.Rendezvous;
import org.springframework.stereotype.Repository;

@Repository
public class RendezVousDAO extends GenericDAO<Rendezvous> {
    public RendezVousDAO() {
        super(Rendezvous.class);
    }
}
