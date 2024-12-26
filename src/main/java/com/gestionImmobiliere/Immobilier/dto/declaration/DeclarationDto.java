package com.gestionImmobiliere.Immobilier.dto.declaration;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeclarationDto {
    private long idDeclaration;
    private String raison;
    private long idUser;
    private String slug;
    private Date dateDeclaration;
    private String slugOrganisme;
    private long idOrganisme;
    private boolean statut = false;

    public Declaration create(DeclarationDto dto, User user, Organisme organisme){
        Declaration declaration = new Declaration();
        declaration.setRaison(dto.getRaison());
        declaration.setOrganisme(organisme);
        declaration.setSlugOrganisme(dto.getSlugOrganisme());
        declaration.setUser(user);

        return declaration;
   }

    public Declaration update(DeclarationDto dto, Declaration declaration){
        declaration.setRaison(dto.getRaison());

        return declaration;
    }

    public Declaration updateStatut(DeclarationDto dto, Declaration declaration){
        declaration.setStatut(dto.isStatut());

        return declaration;
    }
}
