package com.gestionImmobiliere.Immobilier.dto.annonce;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnnonceDto {
    private long idAnnonce;
    private Date datePublication;
    private String titre;
    private String description;
    private String type;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idBien;
    private long idOrganisme;

    public Annonce create(AnnonceDto dto, Bien bien, Organisme organisme){
        Annonce annonce = new Annonce();
        annonce.setDatePublication(dto.getDatePublication());
        annonce.setDescription(dto.getDescription());
        annonce.setTitre(dto.getTitre());
        annonce.setType(dto.getType());
        annonce.setSlugOrganisme(dto.getSlugOrganisme());

        return annonce;
   }

    public Annonce update(AnnonceDto dto, Annonce annonce){
        annonce.setDatePublication(dto.getDatePublication());
        annonce.setDescription(dto.getDescription());
        annonce.setTitre(dto.getTitre());
        annonce.setType(dto.getType());

        return annonce;
    }
}
