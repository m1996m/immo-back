package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.repository.Generic.FactureDAO;
import org.springframework.stereotype.Service;

@Service
public class FactureService extends GenericService<Facture> {
    public FactureService(FactureDAO factureDAO) {
        super(factureDAO);
    }
}
