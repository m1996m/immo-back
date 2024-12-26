package com.gestionImmobiliere.Immobilier.dto.clauseContrat;

import com.gestionImmobiliere.Immobilier.model.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClauseContratDto {
    private long idClauseContrat;
    private String commentaire;
    private String type;
    private LocalDateTime updateAt;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idUser;

    public ClauseContrat create(ClauseContratDto dto, Organisme organisme, User user){
        ClauseContrat clauseContrat = new ClauseContrat();
        clauseContrat.setType(dto.getType());
        clauseContrat.setCommentaire(dto.getCommentaire());
        clauseContrat.setSlugOrganisme(dto.getSlugOrganisme());
        clauseContrat.setOrganisme(organisme);
        clauseContrat.setUser(user);

        return clauseContrat;
   }

    public ClauseContrat update(ClauseContratDto dto, ClauseContrat clauseContrat){
        clauseContrat.setType(dto.getType());
        clauseContrat.setCommentaire(dto.getCommentaire());

        return clauseContrat;
    }
}
