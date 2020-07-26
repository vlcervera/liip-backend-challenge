package org.vlcervera.beer.domain.exception;

/**
 * Represents the domain exception created when we have an error in infrastructure implementation
 * of TranslationStoragePort
 */
public class TranslationStorageException extends RuntimeException {
    public TranslationStorageException(Exception e) {
        super("Error storing numbers translated", e);
    }
}
