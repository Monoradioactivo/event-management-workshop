package mx.simio.spring_workshop.event;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import mx.simio.spring_workshop.participant.ParticipantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static mx.simio.spring_workshop.common.ApiConstant.EVENT_BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(EVENT_BASE_PATH)
public class EventController {

  private final EventService eventService;

  @PostMapping
  public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
    EventDTO createdEvent = eventService.createEvent(eventDTO);
    return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id) {
    EventDTO event = eventService.getEventById(id);
    return ResponseEntity.ok(event);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EventDTO> updateEvent(@PathVariable UUID id,
      @RequestBody EventDTO eventDTO) {
    EventDTO updatedEvent = eventService.updateEvent(id, eventDTO);
    return ResponseEntity.ok(updatedEvent);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
    eventService.deleteEvent(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search")
  public ResponseEntity<Page<EventDTO>> searchEvents(
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) String category,
      @RequestParam(required = false) LocalDate startDate,
      @RequestParam(required = false) LocalDate endDate,
      Pageable pageable) {
    Page<EventDTO> events = eventService.searchEvents(keyword, category, startDate, endDate,
        pageable);
    return ResponseEntity.ok(events);
  }

  @GetMapping("/{id}/ranking")
  public ResponseEntity<List<ParticipantDTO>> getRankingByEventId(@PathVariable UUID id) {
    List<ParticipantDTO> ranking = eventService.getRankingByEventId(id);
    return ResponseEntity.ok(ranking);
  }

}
