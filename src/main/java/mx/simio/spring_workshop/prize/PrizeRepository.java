package mx.simio.spring_workshop.prize;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, UUID> {

  Optional<Prize> findByIdAndEventId(UUID id, UUID eventId);

  Page<Prize> findAllByEventId(UUID eventId, Pageable pageable);

  List<Prize> findAllByEventIdAndQuantityGreaterThan(UUID eventId, int quantity);

  Integer countByEventId(UUID eventId);
}
