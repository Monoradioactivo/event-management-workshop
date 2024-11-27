package mx.simio.spring_workshop.exception;

public class InvalidEventCategoryException extends RuntimeException {

  public InvalidEventCategoryException(String message) {
    super(message);
  }
}
