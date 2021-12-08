package dt.pilot.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import dt.pilot.shared.pojo.response.SimpleResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<SimpleResponse> handleBusinessException(BadRequestException ex,
      WebRequest request) {
    log.error("Bad request exception message={}", ex.getMessage());
    log.debug("Bad request: ", ex);

    return new ResponseEntity<>(new SimpleResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
