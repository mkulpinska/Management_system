package com.company.managementSystem.presentation;

import com.company.managementSystem.dto.ReportRow;
import com.company.managementSystem.model.DataReport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.security.util.FieldUtils.getFieldValue;

public class PrintCSV implements Printer {

    @Override
    public void printReport(DataReport dataReport){
        String fileName = null;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        if (fileName == null || fileName.isBlank()) {
            fileName = timestamp + dataReport.getNameFile() + ".csv";
        }

        File outputDir = new File("exports");

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File outputFile = new File(outputDir, fileName);

        try (FileWriter writer = new FileWriter(outputFile)) {

            List<String> columns = dataReport.getColumnNames();

            writeHeader(writer, columns);
            writeRows(writer, dataReport, columns);

            System.out.println("CSV exported to: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error while writing CSV file.");
            e.printStackTrace();
        }
    }

    private void writeHeader(FileWriter writer, List<String> columns) throws IOException {
        for (int i = 0; i < columns.size(); i++) {
            writer.write(columns.get(i));

            if (i < columns.size() - 1) {
                writer.write(";");
            }
        }

        writer.write(System.lineSeparator());
    }

    private void writeRows(FileWriter writer, DataReport dataReport, List<String> columns) throws IOException {
        if (dataReport.getRows() == null) {
            return;
        }

        for(ReportRow rowRecord: dataReport.getRows()) {

            String[] values = rowRecord.toStringArray();

            int i =0;
            for (String value: values) {
                writer.write(value);

                if (i < values.length - 1) {
                    writer.write(";");
                }


            }
            writer.write(System.lineSeparator());
        }

    }

    private Object getFieldValue(Object row, String column) {
        try {
            Field field = row.getClass().getDeclaredField(column);
            field.setAccessible(true);

            return field.get(row);

        } catch (NoSuchFieldException e) {
            System.out.println("Field not found: " + column);
            return "";

        } catch (IllegalAccessException e) {
            System.out.println("Cannot access field: " + column);
            return "";
        }
    }
}


