package com.company.managementSystem.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record ProjectSummaryRecord(
        String projectName,
        Long timeInHours,
        LocalDate minDate,
        LocalDate maxDate
) implements ReportRow{
    public String[] toStringArray()  {
        return new String[] {
                projectName,
                String.valueOf(timeInHours),
                String.valueOf(minDate),
                String.valueOf(maxDate)
        };
    }
}