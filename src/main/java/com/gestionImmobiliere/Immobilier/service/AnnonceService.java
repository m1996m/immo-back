package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Annonce;
import com.gestionImmobiliere.Immobilier.repository.Generic.AnnonceDAO;
import org.springframework.stereotype.Service;

@Service
public class AnnonceService extends GenericService<Annonce> {
    public AnnonceService(AnnonceDAO annonceDAO) {
        super(annonceDAO);
    }
}
