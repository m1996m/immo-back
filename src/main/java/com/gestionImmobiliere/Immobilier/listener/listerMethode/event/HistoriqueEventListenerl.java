package com.gestionImmobiliere.Immobilier.listener.listerMethode.event;

import com.gestionImmobiliere.Immobilier.keycloak.KeycloakService;
import com.gestionImmobiliere.Immobilier.model.Historique;
import com.gestionImmobiliere.Immobilier.repository.HistoriqueRepository;
import com.gestionImmobiliere.Immobilier.service.UserService;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HistoriqueEventListenerl {

    private final HistoriqueRepository historiqueRepository;
    private final KeycloakService keycloakService;
    private final UserService userService;

    public HistoriqueEventListenerl(
            HistoriqueRepository historiqueRepository,
            KeycloakService keycloakService,
            UserService userService
    ) {
        this.historiqueRepository = historiqueRepository;
        this.keycloakService = keycloakService;
        this.userService = userService;
    }

    @EventListener
    public void handleHistoriqueEvent(HistoriqueEventl event) {
        SecurityContext context = SecurityContextHolder.getContext();

        // Assurez-vous que le contexte est disponible
        if (context == null || context.getAuthentication() == null) {
            throw new IllegalStateException("Utilisateur non authentifié ou contexte de sécurité manquant");
        }

        // Récupérer l'utilisateur connecté
        String email = context.getAuthentication().getName();

        Historique historique = new Historique();
        historique.setAction(event.getAction());
        historique.setDate(LocalDateTime.now());
        historique.setEntiteCible(event.getEntiteCible());
        historique.setTypeAction(event.getTypeAction());
        historique.setUser(userService.findUniqueWithValueAndAttribut(email, "email"));

        historiqueRepository.save(historique);
    }
}

