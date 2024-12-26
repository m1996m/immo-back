package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Declaration;
import com.gestionImmobiliere.Immobilier.repository.Generic.DeclarationDAO;
import org.springframework.stereotype.Service;

@Service
public class DeclarationService extends GenericService<Declaration> {
    public DeclarationService(DeclarationDAO declarationDAO) {
        super(declarationDAO);
    }
}
