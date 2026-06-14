package com.company.managementSystem.dto;

import java.time.LocalDate;

public record ProjectSummaryRecord(
        String projectName,
        Long timeInHours,
        LocalDate minDate,
        LocalDate maxDate
) {}