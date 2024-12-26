package com.gestionImmobiliere.Immobilier.service;

import com.gestionImmobiliere.Immobilier.dto.organisme.OrganismeDto;
import com.gestionImmobiliere.Immobilier.generic.GenericService;
import com.gestionImmobiliere.Immobilier.model.Organisme;
import com.gestionImmobiliere.Immobilier.repository.Generic.OrganismeDAO;
import com.gestionImmobiliere.Immobilier.repository.OrganismeRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganismeService extends GenericService<Organisme> {
    private final OrganismeRepository organismeRepository;

    public OrganismeService(OrganismeDAO organismeDAO, OrganismeRepository organismeRepository) {
        super(organismeDAO);
        this.organismeRepository = organismeRepository;
    }

    public Organisme createOrganisme(OrganismeDto organismeDto){

        return organismeRepository.save(organismeDto.create(organismeDto));
    }
}
