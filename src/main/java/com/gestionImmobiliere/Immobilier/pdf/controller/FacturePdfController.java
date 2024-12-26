package com.gestionImmobiliere.Immobilier.pdf.controller;

import com.gestionImmobiliere.Immobilier.pdf.service.FacturePdfService;
import com.gestionImmobiliere.Immobilier.pdf.service.QuittancePdfService;
import com.gestionImmobiliere.Immobilier.service.FactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FacturePdfController {

    private final FacturePdfService factureService;

    @GetMapping("/api/generatepdf-facture")
    public ResponseEntity<byte[]> generateQuittance(
            @RequestParam long id) {

        byte[] content = factureService.generateFacturePdf(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(content);
    }
}

