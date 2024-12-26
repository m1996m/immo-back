package com.gestionImmobiliere.Immobilier.pdf.service;

import com.gestionImmobiliere.Immobilier.model.*;
import com.gestionImmobiliere.Immobilier.service.FactureService;
import com.gestionImmobiliere.Immobilier.service.QuitanceService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class FacturePdfService {
    private final FactureService factureService;

    public byte[] generateFacturePdf(long id) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String imagePath = "src/main/resources/static/image/logo.png";

        Facture facture = factureService.findUniqueById(id);

        String organisme = formatOrganismeInfo(facture.getContrat().getClauseContrat().getOrganisme());
        String bien = formatBienInfo(facture.getContrat().getBien());
        User user = facture.getContrat().getUser();
        Contrat contrat = facture.getContrat();

        try {
            // Créer un écrivain PDF
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A5);

            // Première ligne avec deux colonnes : Organisme à gauche, Bien à droite
            float[] columnWidths = {280, 280};
            Table headerTable = new Table(columnWidths);
            headerTable.setWidth(com.itextpdf.layout.property.UnitValue.createPercentValue(100));

            // Colonne gauche : Logo et informations de l'organisme
            Image logo = null;
            try {
                ImageData imageData = ImageDataFactory.create(imagePath);
                logo = new Image(imageData).setWidth(80).setHeight(30);
            } catch (Exception ignored) {
                System.out.println("Image non trouvée, elle sera ignorée.");
            }

            Paragraph orgInfo = new Paragraph(organisme)
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.LEFT);

            // Créer les cellules sans bordure
            Cell logoCell = new Cell().add(logo).setBorder(null);
            Cell orgInfoCell = new Cell().add(orgInfo).setBorder(null);

            // Ajouter à la colonne gauche
            Table leftColumn = new Table(1);
            leftColumn.addCell(logoCell);
            leftColumn.addCell(orgInfoCell);

            // Colonne droite : Informations du bien
            Paragraph propertyInfo = new Paragraph(bien)
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT);

            Cell propertyCell = new Cell().add(propertyInfo).setBorder(null);

            // Ajouter les deux colonnes au tableau d'en-tête sans bordure
            headerTable.addCell(new Cell().add(leftColumn).setBorder(null));
            headerTable.addCell(propertyCell);

            // Ajouter la ligne d'en-tête complète au document
            document.add(headerTable);

            Paragraph numero = new Paragraph("Numéro: "+facture.getNumero())
                    .setBold()
                    .setFontSize(13)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(numero);

            // Titre centré
            Paragraph title = new Paragraph("Quittance de Loyer")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);

            // Ajouter la date alignée à gauche
            document.add(new Paragraph("Date d'emission : " + formatDate(new Date())+"\n"));

            // Tableau des informations de la quittance (plein largeur)
            float[] quittanceColumnWidths = {150, 350};
            Table quittanceTable = new Table(quittanceColumnWidths);
            quittanceTable.setWidth(com.itextpdf.layout.property.UnitValue.createPercentValue(100));

            quittanceTable.addCell(new Paragraph("Locataire").setBold());
            quittanceTable.addCell(new Paragraph(user.getFirstName() +" "+ user.getLastName()));
            quittanceTable.addCell(new Paragraph("Periode").setBold());
            quittanceTable.addCell(new Paragraph( facture.getPeriode()));
            quittanceTable.addCell(new Paragraph("Facture à payé avant").setBold());
            quittanceTable.addCell(new Paragraph( formatDate(contrat.getDateEcheance())).setBold()
                    .setFontColor(ColorConstants.RED));
            quittanceTable.addCell(new Paragraph("Montant total à payé").setBold());
            quittanceTable.addCell(new Paragraph(contrat.getMontantMensuel() + " €"));
            quittanceTable.addCell(new Paragraph("Montant payé").setBold());
            quittanceTable.addCell(new Paragraph(facture.getMontantPaye() + " €"));
            quittanceTable.addCell(new Paragraph("Montant restant").setBold());
            quittanceTable.addCell(new Paragraph(facture.getMontantRestant() + " €"))
                    .setBold()
                    .setBackgroundColor(ColorConstants.GRAY)
                    .setFontColor(ColorConstants.WHITE);

            document.add(quittanceTable);

            // Pied de page avec signature
            document.add(
                    new Paragraph("\n\nSignature du propriétaire ")
                            .setTextAlignment(TextAlignment.RIGHT)
            );

            // Fermer le document
            document.close();

            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la génération du PDF : " + e.getMessage());
        }
    }

    private String formatDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String formatBienInfo(Bien bien) {
        if (bien == null) {
            return "Aucune information disponible pour ce bien.";
        }

        return String.format(
                        "Type: %s\n" +
                        "Adresse: %s, %s\n" +
                        "Surface: %s m²\n" +
                        "Prix: %.2f €\n",
                bien.getType(),
                bien.getAdresse(),
                bien.getVille(),
                bien.getSurface(),
                bien.getPrix()
        );
    }

    public String formatOrganismeInfo(Organisme organisme) {
        if (organisme == null) {
            return "Aucune information disponible pour cet organisme.";
        }

        return String.format(
                        "Nom: %s\n" +
                        "Adresse: %s\n" +
                        "Téléphone: %s\n" +
                        "Email: %s\n",
                organisme.getName(),
                organisme.getAddress(),
                organisme.getTel(),
                organisme.getEmail()
        );
    }
}
