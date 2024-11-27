package mx.simio.spring_workshop.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleEventNotFoundException(ResourceNotFoundException ex) {
    ErrorDetails errorDetails = new ErrorDetails(
        ex.getMessage(),
        LocalDateTime.now()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidEventCategoryException.class)
  public ResponseEntity<ErrorDetails> handleInvalidEventCategoryException(
      InvalidEventCategoryException ex) {
    ErrorDetails errorDetails = new ErrorDetails(
        ex.getMessage(),
        LocalDateTime.now()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
    ErrorDetails errorDetails = new ErrorDetails(
        "An unexpected error occurred " + ex.getMessage(),
        LocalDateTime.now()
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
