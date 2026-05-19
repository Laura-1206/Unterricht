package com.mycompany.ehks;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;

public class ReportGenerator {

    public static void generatePDF(ScreeningData screening, String outputPath) throws IOException {
        PdfWriter writer = new PdfWriter(outputPath);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        try {
            PdfFont boldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);
            Paragraph title = new Paragraph("Hautkrebs-Screening Kurzbericht")
                    .setFont(boldFont)
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            PdfFont normalFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA);
            Paragraph patientHeader = new Paragraph("Patientendaten:")
                    .setFont(boldFont)
                    .setFontSize(14);
            document.add(patientHeader);

            document.add(new Paragraph("Name: " + screening.getPatient().getPatientName())
                    .setFont(normalFont));
            document.add(new Paragraph("Versicherten-ID: " + screening.getPatient().getVersichertenId())
                    .setFont(normalFont));
            document.add(new Paragraph("Geburtsdatum: " + screening.getPatient().getGeburtsdatumString())
                    .setFont(normalFont));
            document.add(new Paragraph("Untersuchungsdatum: " + screening.getUntersuchungsdatumString())
                    .setFont(normalFont));
            document.add(new Paragraph("Arzt: " + screening.getPatient().getArztname())
                    .setFont(normalFont));
            document.add(new Paragraph("\n"));

            Paragraph resultsHeader = new Paragraph("Untersuchungsergebnisse:")
                    .setFont(boldFont)
                    .setFontSize(14);
            document.add(resultsHeader);

            document.add(new Paragraph("Verdachtsdiagnose:")
                    .setFont(boldFont));
            if (screening.isMalignesMelanom()) {
                document.add(new Paragraph("  - Malignes Melanom")
                        .setFont(normalFont));
            }
            if (screening.isBasalzellkarzinom()) {
                document.add(new Paragraph("  - Basalzellkarzinom")
                        .setFont(normalFont));
            }
            if (screening.isSpinozellulaeresKarzinom()) {
                document.add(new Paragraph("  - Spinozelluläres Karzinom")
                        .setFont(normalFont));
            }
            if (screening.isAndererHautkrebs()) {
                document.add(new Paragraph("  - Anderer Hautkrebs")
                        .setFont(normalFont));
            }
            if (screening.isSonstigerDermatologischerBefund()) {
                document.add(new Paragraph("  - Sonstiger dermatologisch abklärungsbedürftiger Befund")
                        .setFont(normalFont));
            }

            if (screening.isBiopsieOderExzision()) {
                document.add(new Paragraph("\nHistopathologie:")
                        .setFont(boldFont));
                if (screening.isHistopathologieMalignesMelanom()) {
                    document.add(new Paragraph("  - Malignes Melanom: " +
                        (screening.getMelanomKlassifikation() != null ? screening.getMelanomKlassifikation() : "") +
                        (screening.getMelanomBreslow() != null ? " (" + screening.getMelanomBreslow() + ")" : ""))
                        .setFont(normalFont));
                }
                if (screening.isHistopathologieBasalzellkarzinom()) {
                    document.add(new Paragraph("  - Basalzellkarzinom: " +
                        (screening.getBasalzellHorizontal() != null ? "Horizontal: " + screening.getBasalzellHorizontal() + " mm" : "") +
                        (screening.getBasalzellVertikal() != null ? ", Vertikal: " + screening.getBasalzellVertikal() + " mm" : ""))
                        .setFont(normalFont));
                }
                if (screening.isHistopathologieSpinozellulaeresKarzinom()) {
                    document.add(new Paragraph("  - Spinozelluläres Karzinom: " +
                        (screening.getSpinozellulaeresKlassifikation() != null ? screening.getSpinozellulaeresKlassifikation() : "") +
                        (screening.getSpinozellulaeresGrading() != null ? " (" + screening.getSpinozellulaeresGrading() + ")" : ""))
                        .setFont(normalFont));
                }
            }

            document.add(new Paragraph("\nEmpfehlung:")
                    .setFont(boldFont));

            String recommendationText =
                screening.getStatusColor().equals("#ffaaaa") ? "Dringende weitere Behandlung erforderlich!" :
                screening.getStatusColor().equals("#ffffaa") ? "Regelmäßige Kontrollen empfohlen." :
                "Keine Auffälligkeiten - nächste Routineuntersuchung in 2 Jahren.";

            Paragraph recommendation = new Paragraph(recommendationText)
                .setFont(normalFont)
                .setFontColor(
                    screening.getStatusColor().equals("#ffaaaa") ? ColorConstants.RED :
                    screening.getStatusColor().equals("#ffffaa") ? ColorConstants.ORANGE :
                    ColorConstants.GREEN);

            document.add(recommendation);

        } finally {
            document.close();
        }
    }
}