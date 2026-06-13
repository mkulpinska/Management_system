package com.company.enroller.model;

import java.util.ArrayList;
import java.util.List;

public class Records {

    private List<RecordDto> recordList;

    public Records() {
        this.recordList = new ArrayList<>();
    }

    public void addRecord(RecordDto record) {
        recordList.add(record);
    }

    public List<RecordDto> getRecords() {return recordList;}


    public List<RecordDto> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordDto> recordList) {
        this.recordList = recordList;
    }
}
