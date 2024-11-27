package mx.simio.spring_workshop.prize;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrizeDTO {

  private UUID id;
  private String name;
  private String description;
  private Integer quantity;
  private UUID eventId;
}
