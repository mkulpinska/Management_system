package com.company.managementSystem.presentation;

import com.company.managementSystem.dto.ReportRow;
import com.company.managementSystem.model.DataReport;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.ss.usermodel.Row;

public class ConsolePrinter implements Printer {

    public void printReport(DataReport dataReport) {
        System.out.println("====================================");
        System.out.println(dataReport.getTitle());
        System.out.println("====================================");

        System.out.println(dataReport.getDescription());
        System.out.println();

        System.out.println("Kolumny:");

        for (Object columnName : dataReport.getColumnNames()) {
            System.out.printf("%-25s", columnName);
        }

        String columns ="";
        for(String column: dataReport.getColumnNames()){
            columns = columns + " " + column;
        }

        System.out.println();

        for(ReportRow rowRecord: dataReport.getRows()) {

            String[] values = rowRecord.toStringArray();

            for (String value: values) {
                System.out.printf("%-25s", value);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("------------------------------------");

        System.out.println("Nazwa pliku: " + dataReport.getNameFile());
    }
}