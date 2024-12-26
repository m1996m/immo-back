package com.gestionImmobiliere.Immobilier.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithPdf(String to, String subject, String text, byte[] pdfData, String pdfFileName) throws MessagingException {
        // Création d'un message MIME
        MimeMessage message = mailSender.createMimeMessage();

        // Utiliser MimeMessageHelper pour gérer les pièces jointes
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Configuration des détails de l'email
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true); // "true" pour activer le HTML dans le contenu

        // Attacher le fichier PDF (en mémoire)
        helper.addAttachment(pdfFileName, new ByteArrayResource(pdfData));

        // Envoi de l'email
        mailSender.send(message);
    }
}
