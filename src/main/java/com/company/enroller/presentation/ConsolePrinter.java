package com.company.enroller.presentation;

import com.company.enroller.model.DataReport;

public abstract class ConsolePrinter implements Printer {

    public void printReport(DataReport dataReport) {
        System.out.println("====================================");
        System.out.println(dataReport.getTitle());
        System.out.println("====================================");

        System.out.println(dataReport.getDescription());
        System.out.println();

        System.out.println("Kolumny:");

        for (String columnName : dataReport.getNameColumns()) {
            System.out.printf("%-25s", columnName);
        }

        System.out.println();
        System.out.println("------------------------------------");

        System.out.println("Nazwa pliku: " + dataReport.getNameFile());
        }
}