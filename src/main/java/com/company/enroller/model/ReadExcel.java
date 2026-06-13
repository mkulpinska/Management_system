package com.company.enroller.model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class ReadExcel {

    public static void main(String[] args) throws IOException {

        try (FileInputStream file = new FileInputStream(
                "C:/Szkolenia/studia/pracownia/reporter-dane/2012/01/Nowak_Piotr.xls");
             Workbook workbook = new HSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0)
                    continue;

                Date name = row.getCell(0).getDateCellValue();
                String score = row.getCell(1).getStringCellValue();

                System.out.println(name + " " + score);
            }
        }
    }
}