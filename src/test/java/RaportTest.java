import com.company.managementSystem.model.WorkRecord;
import com.company.managementSystem.model.WorkRecords;
import com.company.managementSystem.service.EmployeeReport;
import com.company.managementSystem.service.Report;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaportTest {

    @Test
    public void simpleRortTest() {
        LocalDate date = LocalDate.of(2020, 1, 8);
        WorkRecord recordDto = new WorkRecord(date, "task", 8,"fileName","projekt1");
        WorkRecords records = new WorkRecords();
        records.addRecord(recordDto);

        Report employeeReport = new EmployeeReport();
        employeeReport.generateReport();
        assertEquals("Raport 1 - Czas pracy pracowników przy projektach", employeeReport.generateReport().getTitle(), "Nieprawidłowy tytul");
    }
}