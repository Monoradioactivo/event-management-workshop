package mx.simio.spring_workshop.participant;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

  Page<Participant> findByIsActiveTrueAndEventId(UUID eventId, Pageable pageable);

  List<Participant> findByIsActiveTrueOrderByScoreDesc(Pageable pageable);

  Integer countByEventId(UUID eventId);

  List<Participant> findTop3ByEventIdOrderByScoreDesc(UUID eventId);
}
