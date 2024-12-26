package com.gestionImmobiliere.Immobilier.dto.clauseContrat;

import com.gestionImmobiliere.Immobilier.dto.contrat.ContratDto;
import com.gestionImmobiliere.Immobilier.model.ClauseContrat;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClauseContratResponseDto {
    private long idClauseContrat;
    private String commentaire;
    private String type;
    private LocalDateTime updateAt;
    private String slug;
    private String slugOrganisme;
    private LocalDateTime createdAt;
    private long idOrganisme;
    private long idUser;
    private List<ContratDto> contrats;

    public ClauseContrat create(ClauseContratResponseDto dto, Organisme organisme, User user){
        ClauseContrat clauseContrat = new ClauseContrat();
        clauseContrat.setType(dto.getType());
        clauseContrat.setSlugOrganisme(dto.getSlugOrganisme());
        clauseContrat.setOrganisme(organisme);
        clauseContrat.setUser(user);

        return clauseContrat;
   }

    public ClauseContrat update(ClauseContratResponseDto dto, ClauseContrat clauseContrat){
        clauseContrat.setType(dto.getType());
        clauseContrat.setSlugOrganisme(dto.getSlugOrganisme());

        return clauseContrat;
    }
}
