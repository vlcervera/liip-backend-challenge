package org.vlcervera.beer.domain.translation;

import lombok.RequiredArgsConstructor;

/**
 * This is an example of a new implementation of Translation to translate all pair numbers with the text pair.
 *
 * This implementation can be done using a instance of DividerTranslation with new DividerTranslation(2,"pair") but
 * it's an example to make another implementations of Translation interface and use it in our use case
 */
@RequiredArgsConstructor
public class PairNumberTranslation implements Translation {
    public String translate(int numberToTranslate) {
        return numberToTranslate % 2 == 0 ? "pair" : NO_TRANSLATION_VALUE;
    }
}
