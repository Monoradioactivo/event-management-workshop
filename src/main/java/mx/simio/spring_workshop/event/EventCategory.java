package mx.simio.spring_workshop.event;

import lombok.Getter;

@Getter
public enum EventCategory {
  HACKATHON("Hackathon"),
  WORKSHOP("Workshop"),
  TECH_TALK("Tech Talk"),
  CONFERENCE("Conference"),
  WEBINAR("Webinar"),
  CODE_CHALLENGE("Code Challenge"),
  NETWORKING("Networking"),
  TEAM_BUILDING("Team Building");

  private final String displayName;

  EventCategory(String displayName) {
    this.displayName = displayName;
  }
}
