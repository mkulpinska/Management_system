package com.company.managementSystem.service;

import com.company.managementSystem.model.WorkRecord;
import com.company.managementSystem.model.WorkRecords;
import com.company.managementSystem.persistence.DatabaseConnector;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReadExcel {
    static DatabaseConnector connector;

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public void run() throws IOException {

        WorkRecords records = new WorkRecords();

        try (FileInputStream file = new FileInputStream(
                "reports/2025/09/Kamiński_Michał.xls");
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

                WorkRecord record = new WorkRecord(date, task, timeInHours, null, null);
                records.addRecord(record);
            }
        }
        System.out.println("Wczytano rekordów: " + records.getWorkRecordList().size());

        connector = DatabaseConnector.getInstance();
        var session = connector.getSession();
        Transaction transaction = session.beginTransaction();

        for (WorkRecord recordDto : records.getWorkRecordList()) {
            session.save(recordDto);
        }

        transaction.commit();
        session.close();
    }
}