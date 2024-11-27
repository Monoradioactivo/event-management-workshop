package mx.simio.spring_workshop.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstant {

  // Base path for the API
  @SuppressWarnings("java:S1075")
  public static final String API_BASE_PATH = "/api/v1";

  // Base path for the events
  public static final String EVENT_BASE_PATH = API_BASE_PATH + "/events";

  // Base path for the prizes
  public static final String PRIZE_BASE_PATH = API_BASE_PATH + "/prizes";

  // Base path for the reports
  public static final String REPORT_BASE_PATH = API_BASE_PATH + "/reports";
}
