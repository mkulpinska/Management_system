package com.company.managementSystem.service;

import com.company.managementSystem.dto.ProjectSummaryRecord;
import com.company.managementSystem.model.DataReport;
import org.hibernate.Session;

import java.util.List;

public class ProjectReport implements Report {
    private final Session session;
    public ProjectReport(Session session) {
        this.session = session;
    }

    @Override
    public DataReport<ProjectSummaryRecord> generateReport() {
        return  new DataReport(
                "Raport 2 - Podsumowanie godzin dla projektów",
                "Raport przedstawia łączną liczbę godzin przypisaną do każdego projektu w określonym zakresie dat.",
                List.of("Nazwa projektu", "Ile godzin", "Zakres dat"),
                "project_report.xlsx");
    }
}