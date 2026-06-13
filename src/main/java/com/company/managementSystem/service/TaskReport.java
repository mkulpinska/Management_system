package com.company.managementSystem.service;

import com.company.managementSystem.model.DataReport;

import java.util.List;

public class TaskReport implements Report {
    @Override
    public DataReport generateReport() {
        return new DataReport(
                "Raport 4 - Zadania zajmujące najwięcej czasu",
                "Raport przedstawia listę zadań posortowaną według liczby przepracowanych godzin.",
                List.of("Godziny", "Projekt", "Zadanie"),
                "task_report.xlsx");
    }

}