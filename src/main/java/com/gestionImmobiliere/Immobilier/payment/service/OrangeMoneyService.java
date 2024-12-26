package com.gestionImmobiliere.Immobilier.payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.*;

import java.util.Base64;

@Service
public class OrangeMoneyService {

    @Value("${orange.api.base-url}")
    private String baseUrl;

    @Value("${orange.api.client-id}")
    private String clientId;

    @Value("${orange.api.client-secret}")
    private String clientSecret;

    @Value("${orange.api.subscription-key}")
    private String subscriptionKey;

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    public OrangeMoneyService(WebClient.Builder webClientBuilder, RestTemplate restTemplate) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.restTemplate = restTemplate;
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

    public String getAccessToken() {
        return webClient.post()
                .uri("/oauth/v2/token")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String initiatePayment(String phoneNumber, String amount, String currency) {
        String token = getAccessToken();

        return webClient.post()
                .uri("/orange-money-webpay/dev/v1/transactions")
                .header("Authorization", "Bearer " + token)
                .bodyValue(new PaymentRequest(phoneNumber, amount, currency))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private static class PaymentRequest {
        private final String phoneNumber;
        private final String amount;
        private final String currency;

        public PaymentRequest(String phoneNumber, String amount, String currency) {
            this.phoneNumber = phoneNumber;
            this.amount = amount;
            this.currency = currency;
        }
    }
}

