package com.company.managementSystem.service;

import com.company.managementSystem.dto.EmployeeSummaryRecord;
import com.company.managementSystem.dto.ReportRow;
import com.company.managementSystem.model.DataReport;
import org.hibernate.Session;

import java.util.List;

public class EmployeeReport implements Report {
    private final Session session;
    public EmployeeReport(Session session) {
        this.session = session;
    }

    @Override
    public DataReport generateReport() {
        DataReport dataReport = new DataReport();

        List<EmployeeSummaryRecord> es = session.createQuery(
                "select new com.company.managementSystem.dto.EmployeeSummaryRecord(wr.userName, sum(wr.timeInHours)) " +
                        "from WorkRecord wr group by wr.userName",
                EmployeeSummaryRecord.class
        ).list();

        dataReport.setRows(es);
        dataReport.setDescription("Employee Report Description");
        dataReport.setTitle("Employee Report Title");
        dataReport.setNameFile("EmployeeReport");
        dataReport.addColumnName("userName");
        dataReport.addColumnName("timeInHours");
        return  dataReport;
    }

}
