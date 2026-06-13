package com.company.enroller.service;

import com.company.enroller.model.DataReport;

public interface Report {
    public void  generateReport();

    public default DataReport getReport(){
        return new DataReport();
    };
}
