package mx.simio.spring_workshop.prize;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mx.simio.spring_workshop.event.Event;
import mx.simio.spring_workshop.event.EventService;
import mx.simio.spring_workshop.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrizeServiceImpl implements PrizeService {

  private final PrizeRepository prizeRepository;
  private final EventService eventService;

  @Override
  public PrizeDTO createPrize(UUID eventId, PrizeDTO prizeDTO) {
    eventService.getEventById(eventId);

    Prize prize = Prize.builder()
        .name(prizeDTO.getName())
        .description(prizeDTO.getDescription())
        .quantity(prizeDTO.getQuantity())
        .event(Event.builder().id(eventId).build())
        .build();

    Prize savedPrize = prizeRepository.save(prize);

    return PrizeDTO.builder()
        .id(savedPrize.getId())
        .name(savedPrize.getName())
        .description(savedPrize.getDescription())
        .quantity(savedPrize.getQuantity())
        .eventId(savedPrize.getEvent().getId())
        .build();
  }

  @Override
  public PrizeDTO getPrizeById(UUID eventId, UUID id) {
    eventService.getEventById(eventId);

    Prize prize = prizeRepository.findByIdAndEventId(id, eventId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Prize with ID " + id + " not found for Event with ID " + eventId));

    return PrizeDTO.builder()
        .id(prize.getId())
        .name(prize.getName())
        .description(prize.getDescription())
        .quantity(prize.getQuantity())
        .eventId(prize.getEvent().getId())
        .build();
  }

  @Override
  public PrizeDTO updatePrize(UUID eventId, UUID id, PrizeDTO prizeDTO) {
    eventService.getEventById(eventId);

    Prize prize = prizeRepository.findByIdAndEventId(id, eventId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Prize with ID " + id + " not found for Event with ID " + eventId));

    prize.setName(prizeDTO.getName());
    prize.setDescription(prizeDTO.getDescription());
    prize.setQuantity(prizeDTO.getQuantity());

    Prize updatedPrize = prizeRepository.save(prize);

    return PrizeDTO.builder()
        .id(updatedPrize.getId())
        .name(updatedPrize.getName())
        .description(updatedPrize.getDescription())
        .quantity(updatedPrize.getQuantity())
        .eventId(updatedPrize.getEvent().getId())
        .build();
  }

  @Override
  public void deletePrize(UUID eventId, UUID id) {
    eventService.getEventById(eventId);

    Prize prize = prizeRepository.findByIdAndEventId(id, eventId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "Prize with ID " + id + " not found for Event with ID " + eventId));

    prizeRepository.delete(prize);
  }

  @Override
  public Page<PrizeDTO> getAllPrizesByEventId(UUID eventId, Pageable pageable) {
    eventService.getEventById(eventId);

    Page<Prize> prizes = prizeRepository.findAllByEventId(eventId, pageable);

    return prizes.map(prize -> PrizeDTO.builder()
        .id(prize.getId())
        .name(prize.getName())
        .description(prize.getDescription())
        .quantity(prize.getQuantity())
        .eventId(prize.getEvent().getId())
        .build());
  }

  @Override
  public List<PrizeDTO> getAvailablePrizes(UUID eventId) {
    eventService.getEventById(eventId);

    List<Prize> prizes = prizeRepository.findAllByEventIdAndQuantityGreaterThan(eventId, 0);

    return prizes.stream()
        .map(prize -> PrizeDTO.builder()
            .id(prize.getId())
            .name(prize.getName())
            .description(prize.getDescription())
            .quantity(prize.getQuantity())
            .eventId(prize.getEvent().getId())
            .build())
        .collect(Collectors.toList());
  }
}
