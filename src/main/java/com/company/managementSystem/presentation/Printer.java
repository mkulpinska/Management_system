package com.company.managementSystem.presentation;

import com.company.managementSystem.model.DataReport;

public interface Printer {
    public void printReport(DataReport<?> dataReport);
}
