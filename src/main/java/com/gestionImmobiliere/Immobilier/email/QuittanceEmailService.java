package com.gestionImmobiliere.Immobilier.email;

import com.gestionImmobiliere.Immobilier.pdf.service.QuittancePdfService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuittanceEmailService {

    private final QuittancePdfService quittancePdfService;
    private final EmailService emailService;

    public void sendQuittanceByEmail(long quittanceId, String recipientEmail) {
        try {
            // Générer le PDF de quittance
            byte[] pdfData = quittancePdfService.generateQuittancePdf(quittanceId);

            // Configuration de l'email
            String subject = "Votre quittance de loyer";
            String text = "<p>Bonjour,</p><p>Veuillez trouver ci-joint votre quittance de loyer.</p>";
            String pdfFileName = "Quittance_Loyer_" + quittanceId + ".pdf";

            // Envoyer l'email avec le PDF en pièce jointe
            emailService.sendEmailWithPdf(recipientEmail, subject, text, pdfData, pdfFileName);

            System.out.println("Quittance envoyée avec succès à " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'envoi de la quittance par email : " + e.getMessage());
        }
    }
}
