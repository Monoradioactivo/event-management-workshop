package mx.simio.spring_workshop.report;

import lombok.RequiredArgsConstructor;
import mx.simio.spring_workshop.event.Event;
import mx.simio.spring_workshop.event.EventCategory;
import mx.simio.spring_workshop.event.EventRepository;
import mx.simio.spring_workshop.participant.Participant;
import mx.simio.spring_workshop.participant.ParticipantRepository;
import mx.simio.spring_workshop.prize.PrizeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final EventRepository eventRepository;
  private final ParticipantRepository participantRepository;
  private final PrizeRepository prizeRepository;

  @Override
  public List<EventSummaryDTO> generateEventSummary(String startDate, String endDate,
      String category, boolean includeDetails) {
    List<Event> events = eventRepository.findAll();

    if (startDate != null && endDate != null) {
      LocalDate start = LocalDate.parse(startDate);
      LocalDate end = LocalDate.parse(endDate);
      events = events.stream()
          .filter(event -> !event.getDate().isBefore(start) && !event.getDate().isAfter(end))
          .toList();
    }

    if (category != null) {
      events = events.stream()
          .filter(event -> event.getCategory().name().equalsIgnoreCase(category))
          .toList();
    }

    return events.stream()
        .map(event -> EventSummaryDTO.builder()
            .eventName(event.getName())
            .eventCategory(event.getCategory().getDisplayName())
            .totalParticipants(participantRepository.countByEventId(event.getId()))
            .totalPrizes(prizeRepository.countByEventId(event.getId()))
            .isActive(event.getIsActive())
            .topParticipants(includeDetails
                ? participantRepository.findTop3ByEventIdOrderByScoreDesc(event.getId()).stream()
                .map(Participant::getName)
                .toList()
                : null)
            .build())
        .toList();
  }

  @Override
  public List<ParticipantPerformanceDTO> generateParticipantPerformance() {
    return participantRepository.findAll().stream()
        .map(participant -> ParticipantPerformanceDTO.builder()
            .participantId(participant.getId())
            .participantName(participant.getName())
            .score(participant.getScore())
            .eventId(participant.getEvent().getId())
            .eventName(participant.getEvent().getName())
            .eventCategory(participant.getEvent().getCategory().name())
            .build())
        .toList();
  }

  @Override
  public List<EventCategory> getAllCategories() {
    return List.of(EventCategory.values());
  }
}
