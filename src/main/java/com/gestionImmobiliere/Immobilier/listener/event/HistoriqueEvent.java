package com.gestionImmobiliere.Immobilier.listener.event;

import com.gestionImmobiliere.Immobilier.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class HistoriqueEvent extends ApplicationEvent {

    private String action;
    private String entiteCible;
    private String typeAction;
    private User user;

    public HistoriqueEvent(Object source, String action, String entiteCible, String typeAction, User user) {
        super(source);
        this.action = action;
        this.entiteCible = entiteCible;
        this.typeAction = typeAction;
        this.user = user;
    }
}

