import com.company.enroller.model.DataReport;
import com.company.enroller.model.RecordDto;
import com.company.enroller.model.Records;
import com.company.enroller.service.EmployeeReport;
import com.company.enroller.service.Report;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaportTest {

    @Test
    public void simpleRortTest() {
        LocalDate date = LocalDate.of(2020, 1, 8);
        RecordDto recordDto = new RecordDto(date, "task", 8,"fileName","projekt1");
        Records records = new Records();
        records.addRecord(recordDto);

        Report employeeReport = new EmployeeReport();
        employeeReport.generateReport(records);
        assertEquals("tytul", employeeReport.getReport().getTitle(), "Nie prawidłowy tytul");
    }
}