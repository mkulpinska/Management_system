package com.company.managementSystem.service;

import com.company.managementSystem.model.DataReport;

public  interface Report {
    public  default DataReport generateReport(){
        return new DataReport();
    };

}
