package com.company.managementSystem.service;

import com.company.managementSystem.model.DataReport;

import java.util.List;

public class ProjectReport implements Report {
    @Override
    public DataReport generateReport() {
        return  new DataReport(
                "Raport 2 - Podsumowanie godzin dla projektów",
                "Raport przedstawia łączną liczbę godzin przypisaną do każdego projektu w określonym zakresie dat.",
                List.of("Nazwa projektu", "Ile godzin", "Zakres dat"),
                "project_report.xlsx");
    }
}