package com.company.managementSystem.service;

import com.company.managementSystem.model.WorkRecord;
import com.company.managementSystem.model.WorkRecords;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ReadExcel {
    private final Session session;

    public ReadExcel(Session session) {
        this.session = session;
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public void run(String path) throws IOException {

        WorkRecords records = new WorkRecords();
        File excelFile = new File(path + "Kamiński_Michał.xls");

        if (!excelFile.exists()) {
            throw new IOException("Plik nie został znaleziony: " + excelFile.getAbsolutePath());
        }

        String userName = excelFile.getName().replace(".xls", "").replace("_", " ");

        String filePath = excelFile.getPath();


        try (FileInputStream file = new FileInputStream(excelFile);

             Workbook workbook = new HSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    // pominięcie nagłówka
                    continue;
                }

              /*  DataFormatter formatter = new DataFormatter();
                DataFormatter formatter = new DataFormatter();
                String dateStr = formatter.formatCellValue(row.getCell(0));
                String task = formatter.formatCellValue(row.getCell(1));
                String hoursStr = formatter.formatCellValue(row.getCell(2));

                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                int timeInHours = Integer.parseInt(hoursStr);

                WorkRecord workRecord = new WorkRecord(date, task, timeInHours, userName, sheet.getSheetName(), filePath);
                records.addRecord(workRecord);*/
                try {

                    DataFormatter formatter = new DataFormatter();

                    String dateStr = formatter.formatCellValue(row.getCell(0)).trim();
                    String task = formatter.formatCellValue(row.getCell(1)).trim();
                    String hoursStr = formatter.formatCellValue(row.getCell(2)).trim();

                    if (dateStr.isEmpty() || task.isEmpty() || hoursStr.isEmpty()) {
                        System.err.println("Pominięto wiersz "
                                + (row.getRowNum() + 1)
                                + " - brak danych.");
                        continue;
                    }

                    LocalDate date = LocalDate.parse(
                            dateStr,
                            DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                    int timeInHours = Integer.parseInt(hoursStr);

                    WorkRecord workRecord = new WorkRecord(
                            date,
                            task,
                            timeInHours,
                            userName,
                            sheet.getSheetName(),
                            filePath);

                    records.addRecord(workRecord);

                } catch (DateTimeParseException e) {
                    System.err.println("Błędna data w wierszu " + (row.getRowNum() + 1));

                } catch (NumberFormatException e) {
                    System.err.println("Błędna liczba godzin w wierszu " + (row.getRowNum() + 1));

                } catch (Exception e) {System.err.println("Błąd w wierszu " + (row.getRowNum() + 1) + ": "
                        + e.getMessage());
                }
            }
        }
        System.out.println("Wczytano rekordów: " + records.getWorkRecords().size());


        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            for (WorkRecord workRecord : records.getWorkRecords()) {
                session.save(workRecord);
            }
            session.flush();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Błąd zapisu do bazy danych: " + e.getMessage());
            throw e;
        }
    }
}