package com.company.managementSystem.model;

import java.util.List;

public class DataReport {
    private String title;
    private String description;
    private List<String> nameColumns;
    private String nameFile;

    public DataReport(){}

    public DataReport(String title, String description, List<String> nameColumns, String nameFile) {
        this.title = title;
        this.description = description;
        this.nameColumns = nameColumns;
        this.nameFile = nameFile;
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public List<String> getNameColumns() {return nameColumns;}
    public void setNameColumns(List<String> nameColumns) {this.nameColumns = nameColumns;}

    public String getNameFile() {return nameFile;}
    public void setNameFile(String nameFile) {this.nameFile = nameFile;}
}
