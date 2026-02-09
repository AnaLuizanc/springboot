package ananc.springboot.handler;

import ananc.springboot.exception.ExceptionDetails;
import ananc.springboot.exception.ResourceNotFoundDetails;
import ananc.springboot.exception.ResourceNotFoundException;
import ananc.springboot.exception.ValidationExceptionDetails;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request
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

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            @NonNull Exception exception,
            @Nullable Object body,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode statusCode,
            @NonNull WebRequest request
    ) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(statusCode.value())
                        .title(exception.getCause().getMessage())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build();

        return new ResponseEntity<>(exceptionDetails, headers, statusCode);
    }
}
