package com.gestionImmobiliere.Immobilier.dto.echange;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EchangeDto {
    private long idEchange;
    private String commentaire;
    private String slug;
    private String slugOrganisme;
    private long idUser;
    private long idOrganisme;

    public Echange create(EchangeDto dto, User user, Organisme organisme){
        Echange echange = new Echange();
        echange.setCommentaire(dto.getCommentaire());
        echange.setSlugOrganisme(dto.getSlugOrganisme());
        echange.setUser(user);
        echange.setOrganisme(organisme);

        return echange;
   }

    public Echange update(EchangeDto dto, Echange echange){
        echange.setCommentaire(dto.getCommentaire());

        return echange;
    }
}
