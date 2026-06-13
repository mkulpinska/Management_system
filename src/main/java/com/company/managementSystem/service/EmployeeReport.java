package com.company.managementSystem.service;

import com.company.managementSystem.dto.EmployeeSummaryRecord;
import com.company.managementSystem.model.DataReport;
import com.company.managementSystem.persistence.DatabaseConnector;
import org.hibernate.Session;

import java.util.List;

public class EmployeeReport implements Report {
    private final Session session;
    public EmployeeReport(Session session) {
        this.session = session;
    }

    @Override
    public DataReport<EmployeeSummaryRecord> generateReport() {
        DataReport<EmployeeSummaryRecord> dataReport = new DataReport<>();

        List<EmployeeSummaryRecord> es = session.createQuery(
                "select new com.company.managementSystem.dto.EmployeeSummaryRecord(wr.userName, sum(wr.timeInHours)) " +
                        "from WorkRecord wr group by wr.userName",
                EmployeeSummaryRecord.class
        ).list();

        return  dataReport;
    }

}
