package mx.simio.spring_workshop.prize;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrizeService {

  PrizeDTO createPrize(UUID eventId, PrizeDTO prizeDTO);

  PrizeDTO getPrizeById(UUID eventId, UUID id);

  PrizeDTO updatePrize(UUID eventId, UUID id, PrizeDTO prizeDTO);

  void deletePrize(UUID eventId, UUID id);

  Page<PrizeDTO> getAllPrizesByEventId(UUID eventId, Pageable pageable);

  List<PrizeDTO> getAvailablePrizes(UUID eventId);
}
