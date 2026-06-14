package com.company.managementSystem.service;

import com.company.managementSystem.model.WorkRecord;
import com.company.managementSystem.model.WorkRecords;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReadExcel {

    private final Session session;

    public ReadExcel(Session session) {
        this.session = session;
    }

    public void run(String path) throws IOException {

        WorkRecords records = new WorkRecords();

        File excelFile = new File(path +"Markowska_Aleksandra.xls");


        if (!excelFile.exists()) {
            throw new IOException("Plik nie został znaleziony: " + excelFile.getAbsolutePath());
        }

        String userName = excelFile.getName()
                .replace(".xls", "")
                .replace("_", " ");

        String filePath = excelFile.getPath();

        try (FileInputStream file = new FileInputStream(excelFile);
             Workbook workbook = new HSSFWorkbook(file)) {

            DataFormatter formatter = new DataFormatter();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            for (Sheet sheet : workbook) {

                System.out.println("Przetwarzanie arkusza: " + sheet.getSheetName());

                if (sheet.getPhysicalNumberOfRows() <= 1) {
                    System.out.println("Pominięto pusty arkusz: " + sheet.getSheetName());
                    continue;
                }

                for (Row row : sheet) {

                    if (row.getRowNum() == 0) {
                        continue;
                    }
                    try {
                        Cell dateCell = row.getCell(0);
                        Cell taskCell = row.getCell(1);
                        Cell hoursCell = row.getCell(2);

                        if (dateCell == null || taskCell == null || hoursCell == null) {

                            System.err.println("Pominięto wiersz " + (row.getRowNum() + 1)
                                    + " w arkuszu " + sheet.getSheetName() + " - brak komórek.");
                            continue;
                        }

                        LocalDate date;

                        if (DateUtil.isCellDateFormatted(dateCell)) {

                            date = dateCell
                                    .getDateCellValue()
                                    .toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();

                        } else {
                            String dateStr = formatter.formatCellValue(dateCell).trim();
                            System.out.println("DATA=[" + dateStr + "]");

                            if (dateStr.isEmpty()) {
                                continue;
                            }
                            date = LocalDate.parse(dateStr, dateFormatter);
                        }
                        String task = formatter.formatCellValue(taskCell).trim();
                        String hoursStr = formatter.formatCellValue(hoursCell).trim();

                        if (task.isEmpty() || hoursStr.isEmpty()) {

                            System.err.println("Pominięto wiersz " + (row.getRowNum() + 1)
                                    + " w arkuszu " + sheet.getSheetName()
                                    + " - brak danych.");
                            continue;
                        }

                        int timeInHours = Integer.parseInt(hoursStr);

                        WorkRecord workRecord = new WorkRecord(
                                date,
                                task,
                                timeInHours,
                                userName,
                                sheet.getSheetName(),
                                filePath
                        );

                        records.addRecord(workRecord);

                    } catch (DateTimeParseException e) {
                        System.err.println("Błędna data w wierszu " + (row.getRowNum() + 1)
                                + " w arkuszu " + sheet.getSheetName());

                    } catch (NumberFormatException e) {
                        System.err.println("Błędna liczba godzin w wierszu " + (row.getRowNum() + 1)
                                + " w arkuszu " + sheet.getSheetName());

                    } catch (Exception e) {
                        System.err.println("Błąd w wierszu " + (row.getRowNum() + 1)
                                + " w arkuszu " + sheet.getSheetName());

                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("Wczytane rekordy: " + records.getWorkRecords().size());

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            for (WorkRecord workRecord : records.getWorkRecords()) {
                System.out.println("Zapisujemy: " + workRecord.getDate() + " | " + workRecord.getTask());

                session.save(workRecord);
            }

            session.flush();
            transaction.commit();

            System.out.println("Commit przechodzi");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Błąd zapisu do bazy danych:");

            e.printStackTrace();
            throw e;
        }
    }
}