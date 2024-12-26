package com.gestionImmobiliere.Immobilier.sms;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public String sendSms(
            @RequestParam String toPhoneNumber,
            @RequestParam String messageContent
    ) {
        return smsService.sendSms(toPhoneNumber, messageContent);
    }
}

