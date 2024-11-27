package mx.simio.spring_workshop.report;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventSummaryDTO {

  private String eventName;
  private String eventCategory;
  private Integer totalParticipants;
  private Integer totalPrizes;
  private Boolean isActive;
  private List<String> topParticipants;
}
