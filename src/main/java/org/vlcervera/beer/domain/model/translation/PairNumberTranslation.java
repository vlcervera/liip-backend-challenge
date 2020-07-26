package org.vlcervera.beer.domain.model.translation;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * This is an example of a new implementation of Translation to translate all pair numbers with the text pair.
 * <p>
 * This implementation can be done using a instance of DivisorTranslation with new DividerTranslation(2,"pair") but
 * it's an example to make another implementations of Translation interface and use it in our use case
 */
@RequiredArgsConstructor
public class PairNumberTranslation implements Translation {
    
    public Optional<String> translate(int numberToTranslate) {
        boolean conditionToTranslate = numberToTranslate % 2 == 0;
        return conditionToTranslate ? Optional.of("pair") : Optional.empty();
    }
}
