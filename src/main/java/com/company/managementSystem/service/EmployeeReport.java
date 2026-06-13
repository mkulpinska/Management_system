package com.company.managementSystem.service;

import com.company.managementSystem.model.DataReport;

import java.util.List;

public class EmployeeReport implements Report {
    @Override
    public DataReport generateReport() {
        return  new DataReport(
                "Raport 1 - Czas pracy pracowników przy projektach",
                "Raport przedstawia liczbę godzin przepracowanych przez pracowników przy konkretnych projektach.",
                List.of("Imię i nazwisko", "Godziny", "Projekt"),
                "employee_report.xlsx"
        );
    }

}
