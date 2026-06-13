package com.company.managementSystem;

import com.company.managementSystem.service.ReadExcel;
import com.company.managementSystem.persistence.DatabaseConnector;


import java.io.IOException;

public class App {

    static DatabaseConnector connector;

    public static void main(String[] args) throws IOException {
        ReadExcel re = new ReadExcel();
        re.run();
    }
}