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
import java.util.Date;

public class ReadExcel {
    private final Session session;
    public ReadExcel(Session session) {
        this.session = session;
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public void run() throws IOException {

        WorkRecords records = new WorkRecords();
        File excelFile = new File("reports/2025/09/Kamiński_Michał.xls");

        String userName = excelFile.getName().replace(".xls", "").replace("_", " ");;
        String filePath = excelFile.getPath();



        try (FileInputStream file = new FileInputStream(excelFile);

             Workbook workbook = new HSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    // pominięcie nagłówka
                    continue;
                }

                DataFormatter formatter = new DataFormatter();
                String dateStr = formatter.formatCellValue(row.getCell(0));
                String task = formatter.formatCellValue(row.getCell(1));
                String hoursStr = formatter.formatCellValue(row.getCell(2));

                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                int timeInHours = Integer.parseInt(hoursStr);

                WorkRecord workRecord = new WorkRecord(date, task, timeInHours, userName, sheet.getSheetName(), filePath);
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