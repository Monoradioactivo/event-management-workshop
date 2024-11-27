package mx.simio.spring_workshop.event;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import mx.simio.spring_workshop.participant.ParticipantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

  EventDTO createEvent(EventDTO eventDTO);

  EventDTO getEventById(UUID id);

  EventDTO updateEvent(UUID id, EventDTO eventDTO);

  void deleteEvent(UUID id);

  Page<EventDTO> searchEvents(String keyword, String category, LocalDate startDate,
      LocalDate endDate, Pageable pageable);

  List<ParticipantDTO> getRankingByEventId(UUID eventId);
}
