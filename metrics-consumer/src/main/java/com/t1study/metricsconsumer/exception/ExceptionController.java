package com.t1study.metricsconsumer.exception;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.error("NoHandlerFoundException: {}", ex.getMessage());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .description("Запрашиваемый ресурс не найден")
                .currentTime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(value = {
            NotFoundException.class,
    })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @Hidden
    public ResponseEntity<ErrorMessage> resourceNotFoundException(RuntimeException exception) {
        log.error("resourceNotFoundException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .description(exception.getMessage())
                        .currentTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            InvalidMediaTypeException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            ValidationException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @Hidden
    public ResponseEntity<ErrorMessage> validationException(RuntimeException exception) {
        log.error("validationException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .description(exception.getMessage())
                        .currentTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @Hidden
    public ResponseEntity<ErrorMessage> unexpectedErrorException(Exception exception) {
        log.error("unexpectedErrorException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .description("Внутренняя ошибка сервера")
                        .currentTime(LocalDateTime.now())
                        .build());
    }
}
