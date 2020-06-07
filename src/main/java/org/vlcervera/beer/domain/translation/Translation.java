package org.vlcervera.beer.domain.translation;

import org.apache.logging.log4j.util.Strings;

/**
 * Common behaviour required in all Translation implementations.
 * <p>
 * Given a number the translation must return a string with the defined number conversion.
 * In case of the number doesn't meet the condition the value retrieved ins NO_TRANSLATION_VALUE
 */
public interface Translation {
    public static String NO_TRANSLATION_VALUE = Strings.EMPTY;

    String translate(int numberToTranslate);
}
