package com.company.managementSystem.service;

import com.company.managementSystem.dto.ReportRow;
import com.company.managementSystem.dto.TaskSummaryRecord;
import com.company.managementSystem.model.DataReport;
import org.hibernate.Session;

import java.util.List;

public class TaskReport implements Report {
    private final Session session;

    public TaskReport(Session session) {
        this.session = session;
    }

    @Override
    public DataReport generateReport() {
        DataReport dataReport = new DataReport();


        List<TaskSummaryRecord> ts = session.createQuery(
                "select new com.company.managementSystem.dto.TaskSummaryRecord(wr.task, sum(wr.timeInHours)) " +
                        "from WorkRecord wr group by wr.task",
                TaskSummaryRecord.class
        ).list();

        dataReport.setRows(ts);
        dataReport.setDescription("Task Report Description");
        dataReport.setTitle("Task Report Title");
        dataReport.addColumnName("task");
        dataReport.addColumnName("timeInHours");

        return dataReport;
    }
}