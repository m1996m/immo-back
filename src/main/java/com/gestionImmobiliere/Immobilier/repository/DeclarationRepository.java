package com.gestionImmobiliere.Immobilier.repository;

import com.gestionImmobiliere.Immobilier.model.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
}
