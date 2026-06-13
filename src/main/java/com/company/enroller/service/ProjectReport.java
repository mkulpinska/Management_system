package com.company.enroller.service;

import com.company.enroller.model.DataReport;
import com.company.enroller.model.Records;
import java.util.List;

public class ProjectReport extends Report {

    public ProjectReport() {
        this.dataReport = new DataReport(
                "Raport 2 - Podsumowanie godzin dla projektów",
                "Raport przedstawia łączną liczbę godzin przypisaną do każdego projektu w określonym zakresie dat.",
                List.of("Nazwa projektu", "Ile godzin", "Zakres dat"),
                "project_report.xlsx"
        );
    }

    @Override
    public void generateReport(Records records) {
        System.out.println("Generowanie raportu projektów...");
    }

    @Override
    public DataReport getReport() {
        return this.dataReport;
    }
}