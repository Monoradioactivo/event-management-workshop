package mx.simio.spring_workshop.event;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

  private UUID id;
  private String name;
  private String description;
  private String category;
  private LocalDate date;
  private Boolean isDeleted;
}

