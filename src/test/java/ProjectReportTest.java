import com.company.managementSystem.dto.ProjectSummaryRecord;
import com.company.managementSystem.model.DataReport;
import com.company.managementSystem.service.ProjectReport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectReportTest {

    @Test
    void emptyQuery_returnsReport() {

        Session session = mock(Session.class);
        Query<ProjectSummaryRecord> query = mock(Query.class);

        when(session.createQuery(anyString(), eq(ProjectSummaryRecord.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of());

        ProjectReport report = new ProjectReport(session);
        DataReport result = report.generateReport();

        assertNotNull(result);
        assertNotNull(result.getRows());
        assertTrue(result.getRows().isEmpty());
    }

    @Test
    void ReportWithCorrectTitleAndDescription() {

        Session session = mock(Session.class);
        Query<ProjectSummaryRecord> query = mock(Query.class);

        when(session.createQuery(anyString(), eq(ProjectSummaryRecord.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of());

        ProjectReport report = new ProjectReport(session);
        DataReport result = report.generateReport();

        assertNotNull(result);
        assertEquals("Raport 2 - Podsumowanie godzin dla projektów", result.getTitle());
        assertEquals("Raport przedstawia łączną liczbę godzin przypisaną do każdego projektu w określonym zakresie dat", result.getDescription());
    }


}
