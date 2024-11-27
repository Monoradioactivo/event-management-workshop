package mx.simio.spring_workshop.prize;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.simio.spring_workshop.common.BaseEntity;
import mx.simio.spring_workshop.event.Event;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prizes")
public class Prize extends BaseEntity {

  private String name;
  private String description;
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;
}
