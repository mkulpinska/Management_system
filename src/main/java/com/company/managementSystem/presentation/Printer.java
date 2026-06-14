package com.company.managementSystem.presentation;

import com.company.managementSystem.model.DataReport;
import com.itextpdf.text.DocumentException;

public interface Printer {
    public void printReport(DataReport dataReport) throws DocumentException, IllegalAccessException;
}
