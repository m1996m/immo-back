package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.FraisAgence;
import com.gestionImmobiliere.Immobilier.repository.Generic.FraisAgenceDAO;
import org.springframework.stereotype.Service;

@Service
public class FraisAgenceService extends GenericService<FraisAgence> {
    public FraisAgenceService(FraisAgenceDAO fraisAgenceDAO) {
        super(fraisAgenceDAO);
    }
}
