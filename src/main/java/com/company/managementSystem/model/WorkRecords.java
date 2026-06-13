package com.company.managementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class WorkRecords {

    private List<WorkRecord> workRecordList;

    public WorkRecords() {
        this.workRecordList = new ArrayList<>();
    }

    public void addRecord(WorkRecord record) {
        workRecordList.add(record);
    }

    public List<WorkRecord> getWorkRecords() {return workRecordList;}

    public void setWorkRecordList(List<WorkRecord> recordList) {
        this.workRecordList = recordList;
    }
}
