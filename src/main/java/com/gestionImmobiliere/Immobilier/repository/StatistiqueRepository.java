package com.gestionImmobiliere.Immobilier.repository;

import com.gestionImmobiliere.Immobilier.model.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistiqueRepository extends JpaRepository<Statistique, Long> {
}
