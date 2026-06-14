package com.company.managementSystem.dto;

import java.io.Serializable;

public record TaskSummaryRecord(String task, Long timeInHours) implements ReportRow {
    public String[] toStringArray()  {
        return new String[] {
                task,
                String.valueOf(timeInHours)
        };
    }
}


