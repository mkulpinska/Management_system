package com.company.managementSystem;

import com.company.managementSystem.persistence.DatabaseConnector;
import com.company.managementSystem.presentation.ConsolePrinter;
import com.company.managementSystem.presentation.Printer;
import com.company.managementSystem.service.*;
import org.hibernate.Session;

import java.io.IOException;

public class App {

    static DatabaseConnector connector;

    public static void main(String[] args) throws IOException {

        boolean runReadExcel = true;
        boolean runReadPdf = false;
        boolean runEmployeeReport = true;
        boolean runProjectReport = false;
        boolean runTaskReport = false;
        Printer printer = new ConsolePrinter();

        //String filePath = args[0];

        String path = "Reports/2025/07/";

        for (String arg : args) {
            if (arg.equals("-g")) {
                runReadExcel = true;
            }
            if (arg.equals("-rEmp")) {
                runEmployeeReport = true;
            }
            if (arg.equals("-rProj")) {
                runProjectReport = true;
            }
            if (arg.equals("-rTask")) {
                runTaskReport = true;
            }
            if (arg.startsWith("-rTyp=")) {
                switch (arg.substring(6)) {
                    case "PDF":
                        printer = new ConsolePrinter();
                        break;
                    case "XML":
                        printer = new ConsolePrinter();
                        break;
                    case "CSV":
                        printer = new ConsolePrinter();
                        break;
                    case "Con":
                        printer = new ConsolePrinter();
                        break;
                }
            }
            if (arg.startsWith("-p=")) {
                path = arg.substring(3);
            }
        }

        Session session = DatabaseConnector.getInstance().getSession();

        if (runReadExcel) {
            new ReadExcel(session).run(path);
        }

        if (runReadPdf) {
            new ReadPdf(session).run();
        }

        if (runEmployeeReport) {
            printer.printReport(new EmployeeReport(session).generateReport());
        }

        if (runProjectReport) {
            printer.printReport(new ProjectReport(session).generateReport());
        }

        if (runTaskReport) {
            printer.printReport(new TaskReport(session).generateReport());
        }

        DatabaseConnector.getInstance().teardown();

    }
}