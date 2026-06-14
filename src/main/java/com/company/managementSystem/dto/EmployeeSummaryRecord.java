package com.company.managementSystem.dto;

public record EmployeeSummaryRecord(String userName, Long hours) implements ReportRow {

    public String[] toStringArray()  {
        return new String[] {
                userName,
                String.valueOf(hours)
        };
    }
}

