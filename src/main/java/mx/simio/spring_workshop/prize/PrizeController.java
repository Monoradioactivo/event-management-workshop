package mx.simio.spring_workshop.prize;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

import static mx.simio.spring_workshop.common.ApiConstant.EVENT_BASE_PATH;

@RestController
@RequiredArgsConstructor
@RequestMapping(EVENT_BASE_PATH + "/{eventId}/prizes")
public class PrizeController {

  private final PrizeService prizeService;

  @PostMapping
  public ResponseEntity<PrizeDTO> createPrize(@PathVariable UUID eventId,
      @RequestBody PrizeDTO prizeDTO) {
    PrizeDTO createdPrize = prizeService.createPrize(eventId, prizeDTO);
    return new ResponseEntity<>(createdPrize, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PrizeDTO> getPrizeById(@PathVariable UUID eventId, @PathVariable UUID id) {
    PrizeDTO prize = prizeService.getPrizeById(eventId, id);
    return new ResponseEntity<>(prize, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Page<PrizeDTO>> getAllPrizesByEventId(@PathVariable UUID eventId,
      Pageable pageable) {
    Page<PrizeDTO> prizes = prizeService.getAllPrizesByEventId(eventId, pageable);
    return new ResponseEntity<>(prizes, HttpStatus.OK);
  }

  @GetMapping("/available")
  public ResponseEntity<List<PrizeDTO>> getAvailablePrizes(@PathVariable UUID eventId) {
    List<PrizeDTO> availablePrizes = prizeService.getAvailablePrizes(eventId);
    return new ResponseEntity<>(availablePrizes, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PrizeDTO> updatePrize(@PathVariable UUID eventId, @PathVariable UUID id,
      @RequestBody PrizeDTO prizeDTO) {
    PrizeDTO updatedPrize = prizeService.updatePrize(eventId, id, prizeDTO);
    return new ResponseEntity<>(updatedPrize, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePrize(@PathVariable UUID eventId, @PathVariable UUID id) {
    prizeService.deletePrize(eventId, id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
