package com.company.enroller.service;

import com.company.enroller.model.DataReport;
import com.company.enroller.model.Records;

public abstract class Report {
    protected DataReport dataReport;
    public  abstract void generateReport(Records records);
    public  DataReport getReport(){
        return dataReport;
    };
}
