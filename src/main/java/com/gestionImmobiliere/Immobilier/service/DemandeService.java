package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Avis;
import com.gestionImmobiliere.Immobilier.model.Demande;
import com.gestionImmobiliere.Immobilier.repository.Generic.AvisDAO;
import com.gestionImmobiliere.Immobilier.repository.Generic.DemandeDAO;
import org.springframework.stereotype.Service;

@Service
public class DemandeService extends GenericService<Demande> {
    public DemandeService(DemandeDAO demandeDAO) {
        super(demandeDAO);
    }
}
