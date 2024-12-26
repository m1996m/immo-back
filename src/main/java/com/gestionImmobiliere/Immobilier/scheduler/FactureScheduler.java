package com.gestionImmobiliere.Immobilier.scheduler;

import com.gestionImmobiliere.Immobilier.model.Contrat;
import com.gestionImmobiliere.Immobilier.model.Facture;
import com.gestionImmobiliere.Immobilier.repository.ContratRepository;
import com.gestionImmobiliere.Immobilier.repository.FactureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FactureScheduler {

    private final ContratRepository contratRepository;

    private final FactureRepository factureRepository;

    // Exécution planifiée : dernier jour du mois à 23:59
    @Scheduled(cron = "59 59 23 L * ?")
    //@Scheduled(cron = "0 * * * * ?") // Exécution chaque minute
    public void genererFacturesMensuelles() {

        // Récupérer la date actuelle et formater la période
        LocalDate today = LocalDate.now();
        String periode = today.format(DateTimeFormatter.ofPattern("MM-yyyy"));

        // Récupérer tous les contrats actifs
        List<Contrat> contratsActifs = contratRepository.findByStatut("ACTIF");

        for (Contrat contrat : contratsActifs) {
            // Vérifier si une facture existe déjà pour cette période
            boolean factureExistante = factureRepository.existsByContratAndPeriode(contrat, periode);

            if (!factureExistante) {
                // Calculer les montants
                double montantTotal = contrat.getMontantMensuel();
                double montantPaye = 0;

                // Créer une nouvelle facture
                Facture facture = Facture.builder()
                        .montantPaye(montantPaye)
                        .montantRestant(montantTotal)
                        .periode(periode)
                        .numero(generateNumeroFacture(contrat.getIdContrat()))
                        .slugOrganisme(contrat.getSlugOrganisme())
                        .contrat(contrat)
                        .build();

                factureRepository.save(facture);
            }
        }
    }

    // Générer un numéro unique pour chaque facture
    private long generateNumeroFacture(long idContrat) {
        return System.currentTimeMillis() + idContrat;
    }
}

