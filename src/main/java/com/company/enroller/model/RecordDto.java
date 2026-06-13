package com.company.enroller.model;

import java.time.LocalDate;

public class RecordDto {

    private LocalDate date;

    private String task;

    private int timeInHours;

    private String userName;

    private String projectName;


    public RecordDto(LocalDate date, String task, int timeInHours, String fileName, String projectName) {
        this.date = date;
        this.task = task;
        this.timeInHours = timeInHours;
        this.userName = fileName;
        this.projectName = projectName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getTimeInHours() {
        return timeInHours;
    }

    public void setTimeInHours(int timeInHours) {
        this.timeInHours = timeInHours;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
