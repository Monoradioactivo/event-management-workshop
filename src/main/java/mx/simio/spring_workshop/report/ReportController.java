package mx.simio.spring_workshop.report;

import lombok.RequiredArgsConstructor;
import mx.simio.spring_workshop.event.EventCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static mx.simio.spring_workshop.common.ApiConstant.REPORT_BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(REPORT_BASE_PATH)
public class ReportController {

  private final ReportService reportService;

  @GetMapping("/event-summary")
  public ResponseEntity<List<EventSummaryDTO>> getEventSummary(
      @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate,
      @RequestParam(required = false) String category,
      @RequestParam(defaultValue = "true") boolean includeDetails) {
    List<EventSummaryDTO> summaries = reportService.generateEventSummary(startDate, endDate, category, includeDetails);
    return ResponseEntity.ok(summaries);
  }


  @GetMapping("/participant-performance")
  public ResponseEntity<List<ParticipantPerformanceDTO>> getParticipantPerformance() {
    List<ParticipantPerformanceDTO> performance = reportService.generateParticipantPerformance();
    return ResponseEntity.ok(performance);
  }

  @GetMapping("/categories")
  public ResponseEntity<List<EventCategory>> getAllCategories() {
    List<EventCategory> categories = reportService.getAllCategories();
    return ResponseEntity.ok(categories);
  }
}
