package mx.simio.spring_workshop.participant;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {

  private UUID id;
  private String name;
  private Integer score;
  private UUID eventId;
  private Boolean isActive;
}
