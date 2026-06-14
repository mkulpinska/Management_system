package com.company.managementSystem.presentation;

import com.company.managementSystem.dto.ReportRow;
import com.company.managementSystem.model.DataReport;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.security.util.FieldUtils.getFieldValue;

public class PDFPrinter implements Printer {
    public void printReport(DataReport dataReport) throws DocumentException, IllegalAccessException {

        File outputDir = new File("exports");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        String fileName = timestamp + dataReport.getNameFile() + ".pdf";
        File outputFile = new File(outputDir, fileName);

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        Paragraph emptyLine = new Paragraph("\n");
        String tab = "         ";

        document.add(new Chunk(dataReport.getTitle(), font));
        document.add(emptyLine);
        document.add(new Chunk(dataReport.getDescription(), font));
        document.add(emptyLine);

        String columns ="";
        for(String column: dataReport.getColumnNames()){
            document.add(new Chunk(column + tab, font));
        }
        document.add(emptyLine);

        for(ReportRow rowRecord: dataReport.getRows()) {

            String[] values = rowRecord.toStringArray();

            for (String value: values) {
                document.add(new Chunk(value+ tab, font));
            }
            document.add(emptyLine);
        }

        document.close();
    }
}
