package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.repository.Generic.BienDAO;
import org.springframework.stereotype.Service;

@Service
public class BienService extends GenericService<Bien> {
    public BienService(BienDAO bienDAO) {
        super(bienDAO);
    }
}
