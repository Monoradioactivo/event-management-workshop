package mx.simio.spring_workshop.event;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import mx.simio.spring_workshop.exception.ResourceNotFoundException;
import mx.simio.spring_workshop.participant.ParticipantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  @Override
  public EventDTO createEvent(EventDTO eventDTO) {
    Event event = EventMapper.mapToEntity(eventDTO);
    Event savedEvent = eventRepository.save(event);
    return EventMapper.mapToDTO(savedEvent);
  }

  @Override
  public EventDTO getEventById(UUID id) {
    Event event = eventRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Event with ID " + id + " not found"));
    return EventMapper.mapToDTO(event);
  }

  @Override
  public EventDTO updateEvent(UUID id, EventDTO eventDTO) {
    Event event = eventRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Event with ID " + id + " not found"));

    event.setName(eventDTO.getName());
    event.setDescription(eventDTO.getDescription());
    event.setCategory(EventMapper.getCategory(eventDTO.getCategory()));
    event.setDate(eventDTO.getDate());

    Event updatedEvent = eventRepository.save(event);
    return EventMapper.mapToDTO(updatedEvent);
  }

  @Override
  public void deleteEvent(UUID id) {
    Event event = eventRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Event with ID " + id + " not found"));

    event.setIsActive(false);
    eventRepository.save(event);
  }

  @Override
  public Page<EventDTO> searchEvents(String keyword, String category, LocalDate startDate,
      LocalDate endDate, Pageable pageable) {
    Page<Event> events = eventRepository.searchEvents(keyword, category, startDate, endDate,
        pageable);
    return events.map(EventMapper::mapToDTO);
  }

  @Override
  public List<ParticipantDTO> getRankingByEventId(UUID eventId) {
    Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new ResourceNotFoundException("Event with ID " + eventId + " not found"));

    return event.getParticipants().stream()
        .sorted((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()))
        .map(participant -> ParticipantDTO.builder()
            .id(participant.getId())
            .name(participant.getName())
            .score(participant.getScore())
            .eventId(eventId)
            .isActive(participant.getIsActive())
            .build())
        .toList();
  }
}
