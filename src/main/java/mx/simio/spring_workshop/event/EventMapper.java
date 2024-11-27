package mx.simio.spring_workshop.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mx.simio.spring_workshop.exception.InvalidEventCategoryException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventMapper {

  public static Event mapToEntity(EventDTO eventDTO) {
    return Event.builder()
        .name(eventDTO.getName())
        .description(eventDTO.getDescription())
        .category(getCategory(eventDTO.getCategory()))
        .date(eventDTO.getDate())
        .build();
  }

  public static EventDTO mapToDTO(Event event) {
    return EventDTO.builder()
        .id(event.getId())
        .name(event.getName())
        .description(event.getDescription())
        .category(event.getCategory().name())
        .date(event.getDate())
        .isDeleted(event.getIsActive())
        .build();
  }

  public static EventCategory getCategory(String category) {
    try {
      return EventCategory.valueOf(category.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new InvalidEventCategoryException("Invalid category: " + category);
    }
  }
}
