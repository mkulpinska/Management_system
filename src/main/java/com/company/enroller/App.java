package com.company.enroller;

import com.company.enroller.model.ReadExcel;
import com.company.enroller.model.RecordDto;
import com.company.enroller.model.Records;
import com.company.enroller.persistence.DatabaseConnector;
import com.company.enroller.service.EmployeeReport;
import com.company.enroller.service.Report;
import org.hibernate.Transaction;


import java.io.IOException;
import java.time.LocalDate;

public class App {

    static DatabaseConnector connector;

    public static void main(String[] args) throws IOException {
        ReadExcel re = new ReadExcel();
        re.run();
    }
}