package com.accountingAPI.accountingSoftware.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

@Service
public class PdfGeneratorService {

    private static final float PAGE_MARGIN = 50;
    private static final PDFont DEFAULT_FONT = PDType1Font.HELVETICA;
    private static final int TEXT_LINE_HEIGHT = 14;

    public byte[] generateTrialBalance(LocalDate startDate, LocalDate endDate, double totalDebits, double totalCredits) {
        PDDocument pdfDoc = new PDDocument();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
           PDPage firstPage = new PDPage(PDRectangle.LETTER);
           pdfDoc.addPage(firstPage);

            try (PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, firstPage)) {
                float currentY = firstPage.getMediaBox().getHeight() - PAGE_MARGIN;

                contentStream.beginText();
                contentStream.setFont(DEFAULT_FONT, 16);
                contentStream.newLineAtOffset(PAGE_MARGIN, currentY);
                contentStream.showText("Trial Balance");

                contentStream.setFont(DEFAULT_FONT, 12);
                currentY -= TEXT_LINE_HEIGHT * 2;
                contentStream.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                contentStream.showText("Period: " + startDate + " to " + endDate);

                // Adding debits
                currentY -= TEXT_LINE_HEIGHT;
                contentStream.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                contentStream.showText(String.format("Total Debits: $%,.2f", totalDebits));

                // Adding credits
                currentY -= TEXT_LINE_HEIGHT;
                contentStream.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                contentStream.showText(String.format("Total Credits: $%,.2f", totalCredits));

                contentStream.endText();
            }

            pdfDoc.save(output);
            return output.toByteArray();
        }
        catch (IOException ex) {
            throw new RuntimeException("Oops, couldn't make Trial Balance PDF", ex); // Made the error message slightly casual
        }
        finally {
            try { pdfDoc.close(); } catch (IOException ignore) {} // A human might forget to properly log this
        }
    }

    public byte[] generateIncomeStatement(LocalDate periodStart, LocalDate periodEnd, double totalRevenue, double totalExpenses) {
        try (PDDocument doc = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
    
            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);
    
            try (PDPageContentStream stream = new PDPageContentStream(doc, page)) {
                float yCoord = page.getMediaBox().getHeight() - PAGE_MARGIN;
    
                stream.beginText();
                stream.setFont(DEFAULT_FONT, 16);
                stream.newLineAtOffset(PAGE_MARGIN, yCoord);
                stream.showText("Income Statement");
    
                stream.setFont(DEFAULT_FONT, 12);
                yCoord -= TEXT_LINE_HEIGHT * 2;
                stream.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                stream.showText("Period: " + periodStart + " to " + periodEnd);
    
                yCoord -= TEXT_LINE_HEIGHT;
                stream.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                stream.showText(String.format("Revenue: $%,.2f", totalRevenue));
    
                yCoord -= TEXT_LINE_HEIGHT;
                stream.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                stream.showText(String.format("Expenses: $%,.2f", totalExpenses));
    
                // Net Income = Revenue - Expenses
                yCoord -= TEXT_LINE_HEIGHT;
                stream.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                double netIncome = totalRevenue - totalExpenses; // Assigned explicitly instead of inline
                stream.showText(String.format("Net Income: $%,.2f", netIncome));
    
                stream.endText();
            }
    
            doc.save(baos);
            return baos.toByteArray();
        }
        catch (IOException err) {
            throw new RuntimeException("Income Statement PDF generation bombed", err);
        }
    }

    public byte[] generateBalanceSheet(LocalDate snapshotDate, double totalAssets, double totalLiabilities) {
        PDDocument document = null;
        ByteArrayOutputStream baos = null;
    
        try {
            document = new PDDocument();
            baos = new ByteArrayOutputStream();
    
            PDPage sheetPage = new PDPage(PDRectangle.LETTER);
            document.addPage(sheetPage);
    
            try (PDPageContentStream writer = new PDPageContentStream(document, sheetPage)) {
                float y = sheetPage.getMediaBox().getHeight() - PAGE_MARGIN;
    
                writer.beginText();
                writer.setFont(DEFAULT_FONT, 16);
                writer.newLineAtOffset(PAGE_MARGIN, y);
                writer.showText("Balance Sheet");
    
                writer.setFont(DEFAULT_FONT, 12);
                y -= TEXT_LINE_HEIGHT * 2;
                writer.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                writer.showText("As of: " + snapshotDate);
    
                y -= TEXT_LINE_HEIGHT;
                writer.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                writer.showText(String.format("Assets: $%,.2f", totalAssets));
    
                y -= TEXT_LINE_HEIGHT;
                writer.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                writer.showText(String.format("Liabilities: $%,.2f", totalLiabilities));
    
                y -= TEXT_LINE_HEIGHT;
                writer.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                double equity = totalAssets - totalLiabilities;
                writer.showText(String.format("Equity: $%,.2f", equity));
    
                writer.endText();
            }
    
            document.save(baos);
            return baos.toByteArray();
        }
        catch (IOException fail) {
            throw new RuntimeException("Could not create Balance Sheet PDF.", fail);
        }
        finally {
            // Free up stuff, might miss exceptions here, but let's fix it later if needed
            if (document != null) {
                try { document.close(); } catch (IOException ignored) {}
            }
        }
    }

    public byte[] generateRetainedEarnings(LocalDate fromDate, LocalDate toDate,
                                       double netProfit, double paidDividends, double finalRetained) {
        try (PDDocument book = new PDDocument();
             ByteArrayOutputStream storage = new ByteArrayOutputStream()) {

            PDPage reportPage = new PDPage(PDRectangle.LETTER);
            book.addPage(reportPage);

            try (PDPageContentStream ctx = new PDPageContentStream(book, reportPage)) {
                float yPos = reportPage.getMediaBox().getHeight() - PAGE_MARGIN;

                ctx.beginText();
                ctx.setFont(DEFAULT_FONT, 16);
                ctx.newLineAtOffset(PAGE_MARGIN, yPos);
                ctx.showText("Retained Earnings Statement");

                ctx.setFont(DEFAULT_FONT, 12);
                yPos -= TEXT_LINE_HEIGHT * 2;
                ctx.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                ctx.showText("Period: " + fromDate + " to " + toDate);

                yPos -= TEXT_LINE_HEIGHT;
                ctx.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                ctx.showText(String.format("Net Income: $%,.2f", netProfit));

                yPos -= TEXT_LINE_HEIGHT;
                ctx.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                ctx.showText(String.format("Dividends: $%,.2f", paidDividends));

                yPos -= TEXT_LINE_HEIGHT;
                ctx.newLineAtOffset(0, -TEXT_LINE_HEIGHT);
                ctx.showText(String.format("Retained Earnings: $%,.2f", finalRetained));

                ctx.endText();
            }

            book.save(storage);
            return storage.toByteArray();
        }
        catch (IOException ioExc) {
            throw new RuntimeException("Error while writing Retained Earnings PDF", ioExc);
        }
    }
}
