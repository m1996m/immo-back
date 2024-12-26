package com.gestionImmobiliere.Immobilier.dto.rendezvous;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RendezvousDto {
    private long idRendezvous;
    private String statut;
    private Date dateRendezvous;
    private String heure;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private String slugAnnonce;
    private long idUser;

    public Rendezvous create(RendezvousDto dto, User user){
        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setDateRendezvous(dto.getDateRendezvous());
        rendezvous.setHeure(dto.getHeure());
        rendezvous.setSlugAnnonce(dto.getSlugAnnonce());
        rendezvous.setSlugOrganisme(dto.getSlugOrganisme());
        rendezvous.setUser(user);

        return rendezvous;
   }

    public Rendezvous update(RendezvousDto dto, Rendezvous rendezvous){
        rendezvous.setDateRendezvous(dto.getDateRendezvous());
        rendezvous.setHeure(dto.getHeure());

        return rendezvous;
    }
}
