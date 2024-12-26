package com.gestionImmobiliere.Immobilier.dto.avis;

import com.gestionImmobiliere.Immobilier.model.Avis;
import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AvisDto {
    private long idAvis;
    private String commentaire;
    private int note;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idBien;
    private long idUser;

    public Avis create(AvisDto dto, Bien bien, User user){
        Avis avis = new Avis();
        avis.setCommentaire(dto.getCommentaire());
        avis.setNote(dto.getNote());
        avis.setSlugOrganisme(dto.getSlugOrganisme());
        avis.setUser(user);
        avis.setBien(bien);

        return avis;
   }

    public Avis update(AvisDto dto, Avis avis){
        avis.setCommentaire(dto.getCommentaire());
        avis.setNote(dto.getNote());

        return avis;
    }
}
