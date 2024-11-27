package mx.simio.spring_workshop.participant;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static mx.simio.spring_workshop.common.ApiConstant.API_BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_BASE_PATH)
public class ParticipantController {

  private final ParticipantService participantService;

  @PostMapping("/participants")
  public ResponseEntity<ParticipantDTO> createParticipant(
      @RequestBody ParticipantDTO participantDTO) {
    ParticipantDTO createdParticipant = participantService.createParticipant(participantDTO);
    return ResponseEntity.status(201).body(createdParticipant);
  }

  @GetMapping("/events/{eventId}/participants")
  public ResponseEntity<Page<ParticipantDTO>> getAllParticipantsByEventId(
      @PathVariable UUID eventId,
      Pageable pageable) {
    Page<ParticipantDTO> participants = participantService.getAllParticipantsByEventId(eventId,
        pageable);
    return ResponseEntity.ok(participants);
  }

  @GetMapping("/participants/{id}")
  public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable UUID id) {
    ParticipantDTO participant = participantService.getParticipantById(id);
    return ResponseEntity.ok(participant);
  }

  @PutMapping("/participants/{id}")
  public ResponseEntity<ParticipantDTO> updateParticipant(
      @PathVariable UUID id,
      @RequestBody ParticipantDTO participantDTO) {
    ParticipantDTO updatedParticipant = participantService.updateParticipant(id, participantDTO);
    return ResponseEntity.ok(updatedParticipant);
  }

  @DeleteMapping("/participants/{id}")
  public ResponseEntity<Void> deactivateParticipant(@PathVariable UUID id) {
    participantService.deactivateParticipant(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/participants/{id}/score")
  public ResponseEntity<ParticipantDTO> updateParticipantScore(
      @PathVariable UUID id,
      @RequestBody ParticipantDTO participantDTO) {
    ParticipantDTO updatedParticipant = participantService.updateParticipantScore(id, participantDTO.getScore());
    return ResponseEntity.ok(updatedParticipant);
  }

  @GetMapping("/participants/top")
  public ResponseEntity<List<ParticipantDTO>> getTopParticipants(
      @RequestParam(defaultValue = "3") int limit) {
    List<ParticipantDTO> topParticipants = participantService.getTopParticipants(limit);
    return ResponseEntity.ok(topParticipants);
  }
}
