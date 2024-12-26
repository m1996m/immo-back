package com.gestionImmobiliere.Immobilier.payment.controller;


import com.gestionImmobiliere.Immobilier.payment.service.MtnMobileMoneyService;
import com.gestionImmobiliere.Immobilier.payment.service.OrangeMoneyService;
import com.gestionImmobiliere.Immobilier.payment.service.PaymentCardService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final MtnMobileMoneyService mtnService;

    private final OrangeMoneyService orangeService;

    private final PaymentCardService paymentCardService;

    /**
     * Endpoint pour initier un paiement via MTN Mobile Money
     */
    @PostMapping("/mtn/initiate")
    public ResponseEntity<String> initiateMtnPayment(
            @RequestParam String externalId,
            @RequestParam String payerNumber,
            @RequestParam String currency,
            @RequestParam int amount) {
        try {
            String response = mtnService.initiatePayment(externalId, payerNumber, currency, amount);
            return ResponseEntity.ok("Paiement MTN initié avec succès : " + response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'initiation du paiement MTN : " + e.getMessage());
        }
    }

    /**
     * Endpoint pour initier un paiement via Orange Money
     */
    @PostMapping("/orange/initiate")
    public ResponseEntity<String> initiateOrangePayment(
            @RequestParam String phoneNumber,
            @RequestParam String currency,
            @RequestParam String amount) {
        try {
            String response = orangeService.initiatePayment(phoneNumber, amount, currency);
            return ResponseEntity.ok("Paiement Orange initié avec succès : " + response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'initiation du paiement Orange : " + e.getMessage());
        }
    }

    /**
     * Endpoint pour vérifier le statut d'un paiement
     */
    @GetMapping("/status")
    public ResponseEntity<String> checkPaymentStatus(
            @RequestParam String provider, // "mtn" ou "orange"
            @RequestParam String transactionId) {
        try {
            String status;
            if ("mtn".equalsIgnoreCase(provider)) {
                status = mtnService.checkPaymentStatus(transactionId);
            } else if ("orange".equalsIgnoreCase(provider)) {
                status = orangeService.checkPaymentStatus(transactionId);
            } else {
                return ResponseEntity.badRequest().body("Fournisseur inconnu : " + provider);
            }
            return ResponseEntity.ok("Statut du paiement : " + status);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la vérification du statut du paiement : " + e.getMessage());
        }
    }

    /**
     * Endpoint pour gérer les notifications de paiement (webhooks)
     */
    @PostMapping("/webhook")
    public ResponseEntity<Void> handlePaymentNotification(@RequestBody String payload) {
        // Traiter la notification
        System.out.println("Notification reçue : " + payload);

        // Vous pouvez ajouter une logique ici pour mettre à jour la base de données
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-checkout-session")
    public String createCheckoutSession(
            @RequestParam String successUrl,
            @RequestParam String cancelUrl,
            @RequestParam Long amount,
            @RequestParam String currency
    ) throws StripeException {
        Session session = paymentCardService.createCheckoutSession(successUrl, cancelUrl, amount, currency);
        return session.getUrl(); // Retourner l'URL de la session Stripe
    }
}

