package org.vlcervera.beer.domain.model.translation;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DivisorTranslation implements Translation {
    private final int    divisor;
    private final String translation;

    public Optional<String> translate(int numberToTranslate) {
        boolean conditionToTranslate = numberToTranslate % divisor == 0;
        return conditionToTranslate ? Optional.ofNullable(translation) : Optional.empty();
    }
}
