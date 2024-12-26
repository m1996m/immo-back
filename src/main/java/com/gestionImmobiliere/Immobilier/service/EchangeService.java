package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Echange;
import com.gestionImmobiliere.Immobilier.repository.Generic.EchangeDAO;
import org.springframework.stereotype.Service;

@Service
public class EchangeService extends GenericService<Echange> {
    public EchangeService(EchangeDAO echangeDAO) {
        super(echangeDAO);
    }
}
