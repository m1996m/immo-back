package com.gestionImmobiliere.Immobilier.payment.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentCardService {
    @Value("${stripe.api-key}")
    private String apiKey;

    public Session createCheckoutSession(String successUrl, String cancelUrl, Long amount, String currency) throws StripeException {
        Stripe.apiKey = apiKey;

        // Créer les paramètres de la session
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency(currency)
                                .setUnitAmount(amount) // Montant en cents (100 = 1.00)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Produit Test")
                                        .build())
                                .build())
                        .build())
                .build();

        // Créer la session de paiement
        return Session.create(params);
    }
}
