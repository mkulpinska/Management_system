package com.company.enroller;

import com.company.enroller.model.RecordDto;
import com.company.enroller.model.Records;
import com.company.enroller.persistence.DatabaseConnector;
import com.company.enroller.service.EmployeeReport;
import com.company.enroller.service.Report;
import org.hibernate.Transaction;


import java.time.LocalDate;

public class App {

    static DatabaseConnector connector;

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2020, 1, 8);
        RecordDto recordDto = new RecordDto(date, "task", 8,"fileName","projekt1");
        Records records = new Records();
        records.addRecord(recordDto);

        connector = DatabaseConnector.getInstance();
        Transaction transaction = connector.getSession().beginTransaction();
        connector.getSession().save(recordDto);
        transaction.commit();

        Report employeeReport = new EmployeeReport();
        employeeReport.generateReport(records);
    }
}