package ananc.springboot.handler;

import ananc.springboot.exception.ResourceNotFoundDetails;
import ananc.springboot.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDetails> handleResourseNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity<ResourceNotFoundDetails>(ResourceNotFoundDetails.builder()
                                                                                  .timestamp(LocalDateTime.now())
                                                                                  .status(HttpStatus.NOT_FOUND.value())
                                                                                  .title("Resourse not found")
                                                                                  .details(exception.getMessage())
                                                                                  .developerMessage(exception.getClass()
                                                                                                             .getName())
                                                                                  .build(), HttpStatus.NOT_FOUND);
    }
}
