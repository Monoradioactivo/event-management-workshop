package mx.simio.spring_workshop.participant;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mx.simio.spring_workshop.common.BaseEntity;
import mx.simio.spring_workshop.event.Event;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participants")
public class Participant extends BaseEntity {

  private String name;
  private Integer score;

  @ManyToOne
  @JoinColumn(name = "event_id", nullable = false)
  private Event event;
}
