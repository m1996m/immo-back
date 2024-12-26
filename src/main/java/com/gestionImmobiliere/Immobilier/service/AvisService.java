package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Avis;
import com.gestionImmobiliere.Immobilier.repository.Generic.AvisDAO;
import org.springframework.stereotype.Service;

@Service
public class AvisService extends GenericService<Avis> {
    public AvisService(AvisDAO avisDAO) {
        super(avisDAO);
    }
}
