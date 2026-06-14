package com.company.managementSystem.presentation;

import com.company.managementSystem.model.DataReport;
import org.apache.poi.ss.usermodel.Row;

public class ConsolePrinter implements Printer {

    public void printReport(DataReport<?> dataReport) {
        System.out.println("====================================");
        System.out.println(dataReport.getTitle());
        System.out.println("====================================");

        System.out.println(dataReport.getDescription());
        System.out.println();

        System.out.println("Kolumny:");

        for (Object columnName : dataReport.getColumnNames()) {
            System.out.printf("%-25s", columnName);
        }

        for(String column: dataReport.getColumnNames()){
            for(Object row: dataReport.getRows()) {
                try {
                    String rowVal = row.getClass().getDeclaredField(column).toString();
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println();
        System.out.println("------------------------------------");

        System.out.println("Nazwa pliku: " + dataReport.getNameFile());
        }
}