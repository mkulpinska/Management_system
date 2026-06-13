package com.company.managementSystem;

import com.company.managementSystem.dto.EmployeeSummaryRecord;
import com.company.managementSystem.model.DataReport;
import com.company.managementSystem.service.EmployeeReport;
import com.company.managementSystem.service.ReadExcel;
import com.company.managementSystem.persistence.DatabaseConnector;


import java.io.IOException;

public class App {

    static DatabaseConnector connector;

    public static void main(String[] args) throws IOException {
        new ReadExcel().run();
        DataReport dataReport = new EmployeeReport().generateReport();

        connector = DatabaseConnector.getInstance();
        connector.teardown();
    }
}