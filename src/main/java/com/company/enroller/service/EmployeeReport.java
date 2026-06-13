package com.company.enroller.service;

import com.company.enroller.model.DataReport;
import com.company.enroller.model.Records;

public class EmployeeReport extends Report {
    public EmployeeReport(){};

    @Override
    public void generateReport(Records records) {
        DataReport dataReport = new DataReport();
        dataReport.setTitle("tytul");

        this.dataReport = dataReport;
    }


}
