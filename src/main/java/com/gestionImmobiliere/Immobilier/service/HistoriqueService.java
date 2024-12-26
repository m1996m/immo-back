package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.model.Historique;
import com.gestionImmobiliere.Immobilier.repository.HistoriqueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HistoriqueService {

    private final HistoriqueRepository historiqueRepository;

    public HistoriqueService(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    public void enregistrerHistorique(String action, Object entity) {
        Historique historique = new Historique();
        historique.setAction(action);
        historique.setDate(LocalDateTime.now());
        historique.setEntiteCible(entity.getClass().getSimpleName());
        historique.setTypeAction(action);

        // Vous pouvez également ajouter d'autres informations si nécessaire
        historiqueRepository.save(historique);
    }
}
