package com.company.managementSystem.service;

import com.company.managementSystem.dto.ProjectSummaryRecord;
import com.company.managementSystem.model.DataReport;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class ProjectReport implements Report {
    private final Session session;

    public ProjectReport(Session session) {
        this.session = session;
    }

    @Override
    public DataReport<ProjectSummaryRecord> generateReport() {
        DataReport<ProjectSummaryRecord> dataReport = new DataReport<>();

        List<ProjectSummaryRecord> projectSummaries = session.createQuery(
                        "select new com.company.managementSystem.dto.ProjectSummaryRecord(" +
                                "wr.projectName, " +
                                "sum(wr.timeInHours), " +
                                "min(wr.date), " +
                                "max(wr.date)) " +
                                "from WorkRecord wr " +
                                "group by wr.projectName",
                        ProjectSummaryRecord.class
                )
                .list();

        dataReport.setRows(projectSummaries);
        dataReport.setTitle("Raport 2 - Podsumowanie godzin dla projektów");
        dataReport.setDescription("Raport przedstawia łączną liczbę godzin przypisaną do każdego projektu w określonym zakresie dat");


        dataReport.addColumnName("projectName");
        dataReport.addColumnName("timeInHours");
        dataReport.addColumnName("dateRange");

        return dataReport;
    }
}