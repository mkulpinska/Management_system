package com.company.managementSystem.presentation;

import com.company.managementSystem.model.DataReport;

public abstract class ConsolePrinter implements Printer {

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

        System.out.println();
        System.out.println("------------------------------------");

        System.out.println("Nazwa pliku: " + dataReport.getNameFile());
        }
}