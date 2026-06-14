import com.company.managementSystem.dto.EmployeeSummaryRecord;
import com.company.managementSystem.dto.ReportRow;
import com.company.managementSystem.model.DataReport;
import com.company.managementSystem.service.EmployeeReport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeReportTest {

    @Test
    void emptyQuery_returnsReport() {
        //mock Hibernate
        Session session = mock(Session.class);
        //tworzy sztuczną sesje
        Query<EmployeeSummaryRecord> query = mock(Query.class);

        //zachowanie mock
        when(session.createQuery(anyString(), eq(EmployeeSummaryRecord.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of());

        EmployeeReport report = new EmployeeReport(session);

        DataReport result = report.generateReport();

        assertNotNull(result);
    }

    @Test
    void simpleQuery_returnsReport() {
        Session session = mock(Session.class);
        Query<EmployeeSummaryRecord> query = mock(Query.class);

        when(session.createQuery(anyString(), eq(EmployeeSummaryRecord.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(new EmployeeSummaryRecord("Jan Nowak", 10L)));

        EmployeeReport report = new EmployeeReport(session);

        DataReport result = report.generateReport();

        assertNotNull(result);
        assertEquals(1, result.getRows().size());

        ReportRow r = result.getRows().get(0);
//        assertEquals("Jan Nowak", r.userName());
//        assertEquals(10L, r.hours());
    }
}
