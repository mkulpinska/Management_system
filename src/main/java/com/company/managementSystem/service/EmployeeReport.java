package com.company.managementSystem.service;

import com.company.managementSystem.model.DataReport;
import com.company.managementSystem.persistence.DatabaseConnector;

public class EmployeeReport implements Report {
    static DatabaseConnector connector;

    @Override
    public DataReport generateReport() {
        DataReport dataReport = new DataReport();

        connector = DatabaseConnector.getInstance();
        var session = connector.getSession();






        session.close();



        return  dataReport;
    }

}
