package com.gestionImmobiliere.Immobilier.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuittanceEmailController {

    private final QuittanceEmailService quittanceEmailService;

    @GetMapping("/send-quittance")
    public String sendQuittance(@RequestParam long quittanceId, @RequestParam String email) {
        try {
            quittanceEmailService.sendQuittanceByEmail(quittanceId, email);
            return "Quittance envoyée avec succès à " + email;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'envoi de la quittance : " + e.getMessage();
        }
    }
}

