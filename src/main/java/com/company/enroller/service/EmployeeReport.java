package com.company.enroller.service;

import com.company.enroller.model.DataReport;
import com.company.enroller.model.Records;

import java.util.List;

public class EmployeeReport extends Report {

    private final DataReport dataReport;

    public EmployeeReport() {
        this.dataReport = new DataReport(
                "Raport 1 - Czas pracy pracowników przy projektach",
                "Raport przedstawia liczbę godzin przepracowanych przez pracowników przy konkretnych projektach.",
                List.of("Imię i nazwisko", "Godziny", "Projekt"),
                "employee_report.xlsx"
        );
    }

    @Override
    public void generateReport(Records records) {

    }

    @Override
    public DataReport getReport() {
        return dataReport;
    }
}
