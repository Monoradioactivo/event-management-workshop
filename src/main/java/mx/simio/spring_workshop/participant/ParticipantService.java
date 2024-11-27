package mx.simio.spring_workshop.participant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ParticipantService {

  ParticipantDTO createParticipant(ParticipantDTO participantDTO);

  Page<ParticipantDTO> getAllParticipantsByEventId(UUID eventId, Pageable pageable);

  ParticipantDTO getParticipantById(UUID id);

  ParticipantDTO updateParticipant(UUID id, ParticipantDTO participantDTO);

  void deactivateParticipant(UUID id);

  ParticipantDTO updateParticipantScore(UUID id, Integer score);

  List<ParticipantDTO> getTopParticipants(int limit);
}
