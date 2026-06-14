package com.company.managementSystem.service;

import com.company.managementSystem.model.WorkRecord;
import com.company.managementSystem.model.WorkRecords;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReadPdf {

    private final Session session;

    public ReadPdf(Session session) {
        this.session = session;
    }

    public void run() throws IOException {

        WorkRecords records = new WorkRecords();

        File pdfFile = new File("reports/2025/06/Żuk_Mirosława.pdf");

        String userName = pdfFile.getName()
                .replace(".pdf", "")
                .replace("_", " ");

        String filePath = pdfFile.getPath();

        try (PDDocument document = Loader.loadPDF(pdfFile)) {

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            String[] lines = text.split("\\R");

            boolean headerSkipped = false;

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                // pominięcie nagłówka
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length < 3) {
                    continue;
                }

                String dateStr = parts[0];
                String hoursStr = parts[parts.length - 1];

                StringBuilder taskBuilder = new StringBuilder();

                for (int i = 1; i < parts.length - 1; i++) {
                    taskBuilder.append(parts[i]).append(" ");
                }

                String task = taskBuilder.toString().trim();

                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d.MM.yyyy"));

                int timeInHours = Integer.parseInt(hoursStr);

                WorkRecord workRecord = new WorkRecord(
                        date,
                        task,
                        timeInHours,
                        userName,
                        "PDF",
                        filePath
                );
                records.addRecord(workRecord);
            }
        }
        System.out.println("Wczytano rekordów: " + records.getWorkRecords().size());

        Transaction transaction = session.beginTransaction();

        for (WorkRecord workRecord : records.getWorkRecords()) {
            session.save(workRecord);
        }
        session.flush();
        transaction.commit();
    }
}