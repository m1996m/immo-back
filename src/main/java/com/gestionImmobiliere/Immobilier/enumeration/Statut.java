package com.gestionImmobiliere.Immobilier.enumeration;

import lombok.Getter;

@Getter
public enum Statut {
    ATTENTE("En attente"),
    REFFUSE("Refusé"),
    ACCEPTE("Accepté");

    private final String displayName;

    Statut(String displayName){
        this.displayName = displayName;
    }
}
