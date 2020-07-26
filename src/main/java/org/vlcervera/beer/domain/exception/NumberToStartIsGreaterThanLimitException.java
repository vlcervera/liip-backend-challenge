package org.vlcervera.beer.domain.exception;

import lombok.Getter;

/**
 * Represents the domain exception created to detail the error when numberToStart y greater than limit defined
 */
@Getter
public class NumberToStartIsGreaterThanLimitException extends RuntimeException {

    private final int numberToStart;
    private final int limit;

    public NumberToStartIsGreaterThanLimitException(int numberToStart, int limit) {
        super(String.format("Error in translation process, start number %d is greater than limit %d number", numberToStart, limit));
        this.numberToStart = numberToStart;
        this.limit         = limit;
    }
}
