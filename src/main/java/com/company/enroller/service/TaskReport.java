package com.company.enroller.service;

import com.company.enroller.model.DataReport;
import com.company.enroller.model.Records;

import java.util.List;

public class TaskReport extends Report {

    private final DataReport dataReport;

    public TaskReport() {
        this.dataReport = new DataReport(
                "Raport 4 - Zadania zajmujące najwięcej czasu",
                "Raport przedstawia listę zadań posortowaną według liczby przepracowanych godzin.",
                List.of("Godziny", "Projekt", "Zadanie"),
                "task_report.xlsx"
        );
    }

    @Override
    public void generateReport(Records records) {
        System.out.println("Generowanie raportu zadań...");
    }

    @Override
    public DataReport getReport() {
        return dataReport;
    }
}