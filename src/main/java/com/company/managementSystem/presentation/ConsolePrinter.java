package com.company.managementSystem.presentation;

import com.company.managementSystem.dto.ReportRow;
import com.company.managementSystem.model.DataReport;

public class ConsolePrinter implements Printer {

    @Override
    public void printReport(DataReport dataReport) {

        System.out.println("====================================");
        System.out.println(dataReport.getTitle());
        System.out.println("====================================");

        System.out.println(dataReport.getDescription());
        System.out.println();

        // Nagłówki kolumn
        for (String columnName : dataReport.getColumnNames()) {
            System.out.printf("%-25s", columnName);
        }
        System.out.println();

        // Separator pod nagłówkami
        for (int i = 0; i < dataReport.getColumnNames().size(); i++) {
            System.out.printf("%-25s", "--------------------");
        }
        System.out.println();

        // Wiersze danych
        for (ReportRow row : dataReport.getRows()) {
            String[] values = row.toStringArray();

            for (String value : values) {
                System.out.printf("%-25s", value);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Nazwa pliku: " + dataReport.getNameFile());
    }
}