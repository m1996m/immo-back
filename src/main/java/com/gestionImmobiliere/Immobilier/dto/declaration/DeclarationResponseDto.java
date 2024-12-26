package com.gestionImmobiliere.Immobilier.dto.declaration;

import com.gestionImmobiliere.Immobilier.model.Declaration;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeclarationResponseDto {
    private long idDeclaration;
    private String raison;
    private String slug;
    private Date dateDeclaration;
    private String slugOrganisme;
    private long idUser;
    private long idOrganisme;
    private boolean statut = false;
}
