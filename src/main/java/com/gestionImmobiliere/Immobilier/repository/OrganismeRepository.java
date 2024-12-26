package com.gestionImmobiliere.Immobilier.repository;

import com.gestionImmobiliere.Immobilier.model.Organisme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganismeRepository extends JpaRepository<Organisme, Long> {
}
