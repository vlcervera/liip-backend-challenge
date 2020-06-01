package org.vlcervera.beer.infrastructure.web.exception_handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vlcervera.beer.domain.exception.NumberToStartIsGreaterThanLimitException;
import org.vlcervera.beer.domain.exception.TranslationStorageException;

@RestControllerAdvice
@Slf4j
public class TranslationExceptionHandler {

    @ExceptionHandler({NumberToStartIsGreaterThanLimitException.class})
    public ResponseEntity<Object> handleException(NumberToStartIsGreaterThanLimitException exception) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ApiError.Type.NUMBER_OF_START_EXCEEDS_LIMIT);
        apiError.getErrors().add(String.format("Number to start is %d", exception.getNumberToStart()));
        apiError.getErrors().add(String.format("limit is %d", exception.getLimit()));

        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({TranslationStorageException.class})
    public ResponseEntity<Object> handleException(TranslationStorageException exception) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ApiError.Type.ERROR_IN_STORAGE);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error("Unknown error",exception);
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ApiError.Type.UNKNOWN);
        apiError.getErrors().add("Server error, please contact with support team");

        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}