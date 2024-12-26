package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Paiement;
import com.gestionImmobiliere.Immobilier.repository.Generic.PaiementDAO;
import org.springframework.stereotype.Service;

@Service
public class PaiementService extends GenericService<Paiement> {
    public PaiementService(PaiementDAO paiementDAO) {
        super(paiementDAO);
    }
}
