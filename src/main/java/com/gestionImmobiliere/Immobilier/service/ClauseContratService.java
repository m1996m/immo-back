package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.ClauseContrat;
import com.gestionImmobiliere.Immobilier.repository.Generic.ClauseContrantDAO;
import org.springframework.stereotype.Service;

@Service
public class ClauseContratService extends GenericService<ClauseContrat> {
    public ClauseContratService(ClauseContrantDAO clauseContrantDAO) {
        super(clauseContrantDAO);
    }
}
