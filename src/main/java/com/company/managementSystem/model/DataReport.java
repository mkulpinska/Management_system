package com.company.managementSystem.model;


import java.util.ArrayList;
import java.util.List;

public class DataReport<R> {
    private String title;
    private String description;
    private List<String> columnNames;
    private String nameFile;
    private List<R> rows;

    public DataReport(){
        columnNames = new ArrayList<>();
    }

    public DataReport(String title, String description, List<String> columnNames, String nameFile) {
        this.title = title;
        this.description = description;
        this.columnNames = columnNames;
        this.nameFile = nameFile;
    }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public List<String> getColumnNames() {return columnNames;}
    public void setColumnNames(List<String> columnNames) {this.columnNames = columnNames;}

    public void addColumnName(String columnName) {this.columnNames.add(columnName);}

    public String getNameFile() {return nameFile;}
    public void setNameFile(String nameFile) {this.nameFile = nameFile;}

    public List<R> getRows() { return rows; }
    public void setRows(List<R> rows) { this.rows = rows; }
}
