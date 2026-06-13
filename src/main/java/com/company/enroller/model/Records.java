package com.company.enroller.model;

import java.util.List;

public class Records {

    private List<Record> recordList;

    public void addRecord(Record record) {
        recordList.add(record);
    }

    public Records(List<Record> records) {
        this.recordList = records;
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }
}
