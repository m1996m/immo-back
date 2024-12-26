package com.gestionImmobiliere.Immobilier.repository;

import com.gestionImmobiliere.Immobilier.model.Quitance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuitanceRepository extends JpaRepository<Quitance, Long> {
}
