package mx.simio.spring_workshop.exception;

import java.time.LocalDateTime;

public record ErrorDetails(String message, LocalDateTime timestamp) {

}
