package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.repository.ContratRepository;
import com.gestionImmobiliere.Immobilier.repository.Generic.ContratDAO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContratService extends GenericService<Contrat> {
    private final ContratRepository contratRepository;

    public ContratService(ContratDAO contratDAO, ContratRepository contratRepository) {
        super(contratDAO);
        this.contratRepository = contratRepository;
    }

    public List<Contrat> findAllContratWithStatut(String statut){
        return contratRepository.findByStatut(statut);
    }
}
