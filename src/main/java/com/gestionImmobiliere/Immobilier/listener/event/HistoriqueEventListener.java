package com.gestionImmobiliere.Immobilier.listener.event;

import com.gestionImmobiliere.Immobilier.model.Historique;
import com.gestionImmobiliere.Immobilier.repository.HistoriqueRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HistoriqueEventListener {

    private final HistoriqueRepository historiqueRepository;

    public HistoriqueEventListener(HistoriqueRepository historiqueRepository) {
        this.historiqueRepository = historiqueRepository;
    }

    @EventListener
    public void handleHistoriqueEvent(HistoriqueEvent event) {
        Historique historique = new Historique();
        historique.setAction(event.getAction());
        historique.setDate(LocalDateTime.now());
        historique.setEntiteCible(event.getEntiteCible());
        historique.setTypeAction(event.getTypeAction());
        historiqueRepository.save(historique);
    }
}

