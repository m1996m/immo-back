package com.gestionImmobiliere.Immobilier.listener.listerMethode.event;

import com.gestionImmobiliere.Immobilier.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HistoriqueEventl {

    private final String action;
    private final String entiteCible;
    private final String typeAction;
    private final User user;

    public HistoriqueEventl(String action, String entiteCible, String typeAction, User user) {
        this.action = action;
        this.entiteCible = entiteCible;
        this.typeAction = typeAction;
        this.user = user;
    }
}

