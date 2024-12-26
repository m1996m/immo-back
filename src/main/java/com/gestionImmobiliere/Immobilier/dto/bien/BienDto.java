package com.gestionImmobiliere.Immobilier.dto.bien;

import com.gestionImmobiliere.Immobilier.model.Bien;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BienDto {
    private long idBien;
    private String type;
    private String adresse;
    private String ville;
    private String slugOrganisme;
    private int nombrePiece;
    private String surface;
    private double prix;
    private String description;
    private long idOrganisme;
    private String slug;
    private LocalDateTime createdAt;

    public Bien create(BienDto dto, Organisme organisme){
        Bien bien = new Bien();
        bien.setType(dto.getType());
        bien.setAdresse(dto.getAdresse());
        bien.setVille(dto.getVille());
        bien.setNombrePiece(dto.getNombrePiece());
        bien.setSurface(dto.getSurface());
        bien.setDescription(dto.getDescription());
        bien.setPrix(dto.getPrix());
        bien.setOrganisme(organisme);
        bien.setSlugOrganisme(dto.slugOrganisme);

        return bien;
   }

    public Bien update(BienDto dto, Bien bien){
        bien.setType(dto.getType());
        bien.setAdresse(dto.getAdresse());
        bien.setVille(dto.getVille());
        bien.setNombrePiece(dto.getNombrePiece());
        bien.setSurface(dto.getSurface());
        bien.setDescription(dto.getDescription());
        bien.setPrix(dto.getPrix());

        return bien;
    }
}
