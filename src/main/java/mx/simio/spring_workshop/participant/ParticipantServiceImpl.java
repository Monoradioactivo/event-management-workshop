package mx.simio.spring_workshop.participant;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import mx.simio.spring_workshop.event.Event;
import mx.simio.spring_workshop.event.EventService;
import mx.simio.spring_workshop.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

  private final ParticipantRepository participantRepository;
  private final EventService eventService;

  @Override
  public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
    eventService.getEventById(participantDTO.getEventId());

    Participant participant = Participant.builder()
        .name(participantDTO.getName())
        .score(participantDTO.getScore())
        .event(Event.builder().id(participantDTO.getEventId()).build())
        .isActive(true)
        .build();

    Participant savedParticipant = participantRepository.save(participant);

    return ParticipantDTO.builder()
        .id(savedParticipant.getId())
        .name(savedParticipant.getName())
        .score(savedParticipant.getScore())
        .eventId(savedParticipant.getEvent().getId())
        .isActive(savedParticipant.getIsActive())
        .build();
  }

  @Override
  public Page<ParticipantDTO> getAllParticipantsByEventId(UUID eventId, Pageable pageable) {
    Page<Participant> participants = participantRepository.findByIsActiveTrueAndEventId(eventId,
        pageable);

    return participants.map(participant -> ParticipantDTO.builder()
        .id(participant.getId())
        .name(participant.getName())
        .score(participant.getScore())
        .eventId(participant.getEvent().getId())
        .isActive(participant.getIsActive())
        .build());
  }

  @Override
  public ParticipantDTO getParticipantById(UUID id) {
    Participant participant = participantRepository.findById(id)
        .filter(Participant::getIsActive)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Participant with ID " + id + " not found or is inactive"));

    return ParticipantDTO.builder()
        .id(participant.getId())
        .name(participant.getName())
        .score(participant.getScore())
        .eventId(participant.getEvent().getId())
        .isActive(participant.getIsActive())
        .build();
  }

  @Override
  public ParticipantDTO updateParticipant(UUID id, ParticipantDTO participantDTO) {
    Participant participant = participantRepository.findById(id)
        .filter(Participant::getIsActive)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Participant with ID " + id + " not found or is inactive"));

    participant.setName(participantDTO.getName());
    participant.setScore(participantDTO.getScore());

    Participant updatedParticipant = participantRepository.save(participant);

    return ParticipantDTO.builder()
        .id(updatedParticipant.getId())
        .name(updatedParticipant.getName())
        .score(updatedParticipant.getScore())
        .eventId(updatedParticipant.getEvent().getId())
        .isActive(updatedParticipant.getIsActive())
        .build();
  }

  @Override
  public void deactivateParticipant(UUID id) {
    Participant participant = participantRepository.findById(id)
        .filter(Participant::getIsActive)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Participant with ID " + id + " not found or is already inactive"));

    participant.setIsActive(false);
    participantRepository.save(participant);
  }

  @Override
  public ParticipantDTO updateParticipantScore(UUID id, Integer score) {
    Participant participant = participantRepository.findById(id)
        .filter(Participant::getIsActive)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Participant with ID " + id + " not found or is inactive"));

    participant.setScore(score);
    Participant updatedParticipant = participantRepository.save(participant);

    return ParticipantDTO.builder()
        .id(updatedParticipant.getId())
        .name(updatedParticipant.getName())
        .score(updatedParticipant.getScore())
        .eventId(updatedParticipant.getEvent().getId())
        .isActive(updatedParticipant.getIsActive())
        .build();
  }

  @Override
  public List<ParticipantDTO> getTopParticipants(int limit) {
    Pageable pageable = Pageable.ofSize(limit);

    List<Participant> topParticipants = participantRepository.findByIsActiveTrueOrderByScoreDesc(
        pageable);

    return topParticipants.stream()
        .map(participant -> ParticipantDTO.builder()
            .id(participant.getId())
            .name(participant.getName())
            .score(participant.getScore())
            .eventId(participant.getEvent().getId())
            .isActive(participant.getIsActive())
            .build())
        .toList();
  }
}
