import com.company.managementSystem.dto.TaskSummaryRecord;
import com.company.managementSystem.model.DataReport;
import com.company.managementSystem.service.TaskReport;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskReportTest {

    @Test
    void emptyQuery_returnsReport() {
        Session session = mock(Session.class);
        Query<TaskSummaryRecord> query = mock(Query.class);

        when(session.createQuery(anyString(), eq(TaskSummaryRecord.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of());

        TaskReport report = new TaskReport(session);

        DataReport result = report.generateReport();

        assertNotNull(result);
        assertNotNull(result.getRows());
        assertEquals(0, result.getRows().size());
        assertEquals("Task Report Title", result.getTitle());
        assertEquals("Task Report Description", result.getDescription());
    }

    @Test
    void simpleQuery_returnsReport() {
        Session session = mock(Session.class);
        Query<TaskSummaryRecord> query = mock(Query.class);

        when(session.createQuery(anyString(), eq(TaskSummaryRecord.class))).thenReturn(query);
        when(query.list()).thenReturn(List.of(
                new TaskSummaryRecord("Implementacja modułu", 15L)
        ));

        TaskReport report = new TaskReport(session);

        DataReport result = report.generateReport();

        assertNotNull(result);
        assertEquals(1, result.getRows().size());

        TaskSummaryRecord row = (TaskSummaryRecord) result.getRows().get(0);
        assertEquals("Implementacja modułu", row.task());
        assertEquals(15L, row.timeInHours());
    }

   // testy błędów

    @Test
    void queryThrowsException_propagatesException() {
        Session session = mock(Session.class);

        when(session.createQuery(anyString(), eq(TaskSummaryRecord.class)))
                .thenThrow(new RuntimeException("DB error"));

        TaskReport report = new TaskReport(session);

        RuntimeException ex = assertThrows(RuntimeException.class, report::generateReport);
        assertEquals("DB error", ex.getMessage());
    }

    @Test
    void listThrowsException_propagatesException() {
        Session session = mock(Session.class);
        Query<TaskSummaryRecord> query = mock(Query.class);

        when(session.createQuery(anyString(), eq(TaskSummaryRecord.class))).thenReturn(query);
        when(query.list()).thenThrow(new IllegalStateException("List failed"));

        TaskReport report = new TaskReport(session);

        IllegalStateException ex = assertThrows(IllegalStateException.class, report::generateReport);
        assertEquals("List failed", ex.getMessage());
    }

    @Test
    void sessionReturnsNullQuery_throwsNullPointer() {
        Session session = mock(Session.class);

        when(session.createQuery(anyString(), eq(TaskSummaryRecord.class))).thenReturn(null);

        TaskReport report = new TaskReport(session);

        assertThrows(NullPointerException.class, report::generateReport);
    }
}
