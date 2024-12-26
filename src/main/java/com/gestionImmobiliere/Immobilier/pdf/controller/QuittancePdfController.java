package com.gestionImmobiliere.Immobilier.pdf.controller;

import com.gestionImmobiliere.Immobilier.pdf.service.QuittancePdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuittancePdfController {

    private final QuittancePdfService quittancePdfService;

    @GetMapping("/api/generatepdf-quittance")
    public ResponseEntity<byte[]> generateQuittance(
            @RequestParam long id) {

        byte[] content = quittancePdfService.generateQuittancePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=quittance.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(content);
    }
}

