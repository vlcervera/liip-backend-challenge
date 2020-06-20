package org.vlcervera.beer.domain.model.translation;

import org.apache.logging.log4j.util.Strings;

import java.util.Optional;

/**
 * Common behaviour required in all Translation implementations.
 * <p>
 * Given a number the translation must return a string with the defined number conversion.
 * In case of the number doesn't meet the condition the value retrieved ins NO_TRANSLATION_VALUE
 */
public interface Translation {

    Optional<String> translate(int numberToTranslate);
}
