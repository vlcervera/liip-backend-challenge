package org.vlcervera.beer.infrastructure.web.exception_handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private HttpStatus    status;
    private Type          type;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String>  errors;

    public ApiError(HttpStatus status, Type type) {
        super();
        this.status = status;
        this.type   = type;
        this.errors = Lists.newArrayList();
    }

    public ApiError(HttpStatus status, Type type, List<String> errors) {
        super();
        this.status = status;
        this.type   = type;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, Type type, String error) {
        super();
        this.status = status;
        this.type   = type;
        errors      = Arrays.asList(error);
    }

    public enum Type {
        NUMBER_OF_START_EXCEEDS_LIMIT,
        ERROR_IN_STORAGE,
        UNKNOWN;
    }
}



