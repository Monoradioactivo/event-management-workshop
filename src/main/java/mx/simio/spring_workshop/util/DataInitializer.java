package mx.simio.spring_workshop.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.simio.spring_workshop.event.Event;
import mx.simio.spring_workshop.event.EventCategory;
import mx.simio.spring_workshop.event.EventRepository;
import mx.simio.spring_workshop.participant.Participant;
import mx.simio.spring_workshop.participant.ParticipantRepository;
import mx.simio.spring_workshop.prize.Prize;
import mx.simio.spring_workshop.prize.PrizeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

  private final EventRepository eventRepository;
  private final ParticipantRepository participantRepository;
  private final PrizeRepository prizeRepository;

  @Override
  public void run(String... args) {
    // Initialize Events
    if (eventRepository.count() == 0) {
      List<Event> events = List.of(
          Event.builder()
              .name("Hackathon 2024")
              .description("An event for innovative tech solutions.")
              .category(EventCategory.HACKATHON)
              .date(LocalDate.of(2024, 1, 15))
              .isActive(true)
              .build(),
          Event.builder()
              .name("Tech Talk - Spring Boot")
              .description("Learn about advanced Spring Boot techniques.")
              .category(EventCategory.TECH_TALK)
              .date(LocalDate.of(2024, 2, 20))
              .isActive(true)
              .build(),
          Event.builder()
              .name("Networking Night")
              .description("Meet professionals and expand your network.")
              .category(EventCategory.NETWORKING)
              .date(LocalDate.of(2024, 3, 5))
              .isActive(true)
              .build()
      );

      eventRepository.saveAll(events);
      log.info("3 sample events have been added to the database.");
    } else {
      log.info("Events already initialized in the database.");
    }

    // Initialize Participants
    if (participantRepository.count() == 0) {
      List<Event> events = eventRepository.findAll();

      List<Participant> participants = List.of(
          // Participants for Event 1
          Participant.builder().name("Michael Scott").score(85).event(events.get(0)).isActive(true)
              .build(),
          Participant.builder().name("Jim Halpert").score(92).event(events.get(0)).isActive(true)
              .build(),
          Participant.builder().name("Pam Beesly").score(88).event(events.get(0)).isActive(true)
              .build(),
          Participant.builder().name("Dwight Schrute").score(95).event(events.get(0)).isActive(true)
              .build(),

          // Participants for Event 2
          Participant.builder().name("Leslie Knope").score(90).event(events.get(1)).isActive(true)
              .build(),
          Participant.builder().name("Ben Wyatt").score(80).event(events.get(1)).isActive(true)
              .build(),
          Participant.builder().name("Ron Swanson").score(89).event(events.get(1)).isActive(true)
              .build(),
          Participant.builder().name("April Ludgate").score(85).event(events.get(1)).isActive(true)
              .build(),

          // Participants for Event 3
          Participant.builder().name("Andy Bernard").score(87).event(events.get(2)).isActive(true)
              .build(),
          Participant.builder().name("Erin Hannon").score(78).event(events.get(2)).isActive(true)
              .build(),
          Participant.builder().name("Stanley Hudson").score(75).event(events.get(2)).isActive(true)
              .build(),
          Participant.builder().name("Phyllis Vance").score(80).event(events.get(2)).isActive(true)
              .build()
      );

      participantRepository.saveAll(participants);
      log.info("12 participants have been added to the database.");
    } else {
      log.info("Participants already initialized in the database.");
    }

    // Initialize Prizes
    if (prizeRepository.count() == 0) {
      List<Event> events = eventRepository.findAll();

      List<Prize> prizes = List.of(
          // Prizes for Event 1
          Prize.builder().name("MacBook Pro").description("Latest MacBook Pro with M3 chip.")
              .quantity(1)
              .event(events.get(0)).build(),
          Prize.builder().name("iPhone 16 Pro")
              .description("Newest iPhone 15 Pro with advanced features.").quantity(1)
              .event(events.get(0)).build(),
          Prize.builder().name("Apple Watch Ultra")
              .description("Premium Apple Watch for extreme sports enthusiasts.").quantity(1)
              .event(events.get(0)).build(),

          // Prizes for Event 2
          Prize.builder().name("Spring Boot Guide").description("Comprehensive Spring Boot guide.")
              .quantity(5).event(events.get(1)).build(),
          Prize.builder().name("Online Course Coupon")
              .description("Coupon for an advanced Spring Boot course.").quantity(3)
              .event(events.get(1)).build(),
          Prize.builder().name("Certification Voucher")
              .description("Voucher for a Spring Boot certification exam.").quantity(2)
              .event(events.get(1)).build(),

          // Prizes for Event 3
          Prize.builder().name("Networking Book")
              .description("A book about professional networking.").quantity(10)
              .event(events.get(2)).build(),
          Prize.builder().name("Business Card Holder")
              .description("A premium business card holder.").quantity(20).event(events.get(2))
              .build(),
          Prize.builder().name("LinkedIn Premium")
              .description("3-month subscription to LinkedIn Premium.").quantity(5)
              .event(events.get(2)).build()
      );

      prizeRepository.saveAll(prizes);
      log.info("9 prizes have been added to the database.");
    } else {
      log.info("Prizes already initialized in the database.");
    }
  }
}
