package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Rendezvous;
import com.gestionImmobiliere.Immobilier.repository.Generic.RendezVousDAO;
import org.springframework.stereotype.Service;

@Service
public class RendezvousService extends GenericService<Rendezvous> {
    public RendezvousService(RendezVousDAO rendezVousDAO) {
        super(rendezVousDAO);
    }
}
