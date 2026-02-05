package ananc.springboot.handler;

import ananc.springboot.exception.ResourceNotFoundDetails;
import ananc.springboot.exception.ResourceNotFoundException;
import ananc.springboot.exception.ValidationExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {

        List<FieldError> fieldsErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldsErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldsErrors.stream()
                                           .map(FieldError::getDefaultMessage)
                                           .collect(Collectors.joining(", "));

        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                                                              .timestamp(LocalDateTime.now())
                                                              .status(HttpStatus.BAD_REQUEST.value())
                                                              .title("Field Validation Error")
                                                              .details("Check the field(s) below")
                                                              .developerMessage(exception.getClass().getName())
                                                              .fields(fields)
                                                              .fieldsMessage(fieldsMessage)
                                                              .build(), HttpStatus.BAD_REQUEST);
    }
}
