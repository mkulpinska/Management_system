package com.company.managementSystem.service;

import com.company.managementSystem.dto.EmployeeSummaryRecord;
import com.company.managementSystem.model.DataReport;
import com.company.managementSystem.persistence.DatabaseConnector;

import java.util.List;

public class EmployeeReport implements Report {
    static DatabaseConnector connector;

    @Override
    public DataReport generateReport() {
        DataReport dataReport = new DataReport();

        connector = DatabaseConnector.getInstance();
        var session = connector.getSession();

        List<EmployeeSummaryRecord> es = session.createQuery(
                "select new com.company.managementSystem.dto.EmployeeSummaryRecord(wr.userName, sum(wr.timeInHours)) " +
                        "from WorkRecord wr group by wr.userName",
                EmployeeSummaryRecord.class
        ).list();

        return  dataReport;
    }

}
