package com.gestionImmobiliere.Immobilier.repository;

import com.gestionImmobiliere.Immobilier.model.Annonce;
import com.gestionImmobiliere.Immobilier.model.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Long> {
}
