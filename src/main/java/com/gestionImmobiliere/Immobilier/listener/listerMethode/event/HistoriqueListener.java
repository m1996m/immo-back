package com.gestionImmobiliere.Immobilier.listener.listerMethode.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import jakarta.persistence.*;

@Component
public class HistoriqueListener {

    private final ApplicationEventPublisher eventPublisher;

    public HistoriqueListener(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostPersist
    public void afterCreate(Object entity) {
        eventPublisher.publishEvent(new HistoriqueEventl(
                "Création",
                entity.getClass().getSimpleName(),
                "Création", null
        ));
    }

    @PostUpdate
    public void afterUpdate(Object entity) {
        eventPublisher.publishEvent(new HistoriqueEventl(
                "Mise à jour",
                entity.getClass().getSimpleName(),
                "Mise à jour",
                null
                ));
    }

    @PostRemove
    public void afterDelete(Object entity) {
        eventPublisher.publishEvent(new HistoriqueEventl(
                "Suppression",
                entity.getClass().getSimpleName(),
                "Suppression",
                null
        ));
    }
}
