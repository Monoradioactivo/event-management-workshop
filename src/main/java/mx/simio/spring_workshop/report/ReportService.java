package mx.simio.spring_workshop.report;

import java.util.List;
import mx.simio.spring_workshop.event.EventCategory;

public interface ReportService {

  List<EventSummaryDTO> generateEventSummary(String startDate, String endDate, String category,
      boolean includeDetails);

  List<ParticipantPerformanceDTO> generateParticipantPerformance();

  List<EventCategory> getAllCategories();
}
