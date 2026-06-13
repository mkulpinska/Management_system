package com.company.enroller.model;

import com.company.enroller.persistence.DatabaseConnector;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class ReadExcel {
    static DatabaseConnector connector;

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public void run() throws IOException {

        Records records = new Records();

        try (FileInputStream file = new FileInputStream(
                "C:/Szkolenia/studia/pracownia/reporter-dane/2012/01/Kowalski_Jan.xls");
             Workbook workbook = new HSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {

                if (row.getRowNum() == 0) {
                    // pominięcie nagłówka
                    continue;
                }
                Date date = row.getCell(0).getDateCellValue();
                String task = row.getCell(1).getStringCellValue();
                int timeInHours = (int) row.getCell(2).getNumericCellValue();

                RecordDto record = new RecordDto(
                        convertToLocalDateViaSqlDate(date), task, timeInHours, null, null);
                records.addRecord(record);





            }
        }
        System.out.println("Wczytano rekordów: " + records.getRecordList().size());



        for (RecordDto recordDto : records.getRecordList()) {
            connector = DatabaseConnector.getInstance();
            Transaction transaction = connector.getSession().beginTransaction();
            connector.getSession().save(recordDto);
            transaction.commit();
        }
    }
}