package mx.simio.spring_workshop.report;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ParticipantPerformanceDTO {

  private UUID participantId;
  private String participantName;
  private Integer score;
  private UUID eventId;
  private String eventName;
  private String eventCategory;
}
