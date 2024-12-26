package com.gestionImmobiliere.Immobilier.payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.*;

@Service
public class MtnMobileMoneyService {

    @Value("${mtn.api.base-url}")
    private String baseUrl;

    @Value("${mtn.api.subscription-key}")
    private String subscriptionKey;

    @Value("${mtn.api.user-id}")
    private String userId;

    @Value("${mtn.api.api-key}")
    private String apiKey;

    private final WebClient webClient;
    private final RestTemplate restTemplate;


    public MtnMobileMoneyService(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.restTemplate = restTemplate;
    }

    public String getAccessToken() {
        return webClient.post()
                .uri("/collection/token/")
                .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                .header("Authorization", "Basic " + apiKey)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String checkPaymentStatus(String transactionId) {
        String url = baseUrl + "/collection/v1_0/requesttopay/" + transactionId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAccessToken());
        headers.set("Ocp-Apim-Subscription-Key", subscriptionKey);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return response.getBody();
    }

    public String initiatePayment(String externalId, String payerNumber, String currency, int amount) {
        String token = getAccessToken();

        return webClient.post()
                .uri("/collection/v1_0/requesttopay")
                .header("Authorization", "Bearer " + token)
                .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                .bodyValue(new PaymentRequest(externalId, payerNumber, currency, amount))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private static class PaymentRequest {
        private final String externalId;
        private final String payer;
        private final String currency;
        private final int amount;

        public PaymentRequest(String externalId, String payerNumber, String currency, int amount) {
            this.externalId = externalId;
            this.payer = payerNumber;
            this.currency = currency;
            this.amount = amount;
        }
    }
}

