package com.gestionImmobiliere.Immobilier.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account-sid}")
    private String accountSid;

    @Value("${twilio.auth-token}")
    private String authToken;

    @Value("${twilio.phone-number}")
    private String fromPhoneNumber;

    public SmsService() {
        // Default constructor
    }

    @PostConstruct
    public void initializeTwilio() {
        // Initialize Twilio with account SID and Auth Token after dependency injection
        Twilio.init(accountSid, authToken);
    }

    public String sendSms(String toPhoneNumber, String messageContent) {
        try {
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(toPhoneNumber),
                    new com.twilio.type.PhoneNumber(fromPhoneNumber),
                    messageContent // Message content
            ).create();

            return "Message sent successfully with SID: " + message.getSid();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send message: " + e.getMessage();
        }
    }
}
