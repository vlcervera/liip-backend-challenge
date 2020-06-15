package org.vlcervera.beer.domain.model.translation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DivisorTranslation implements Translation {
    private final int    divisor;
    private final String translation;

    public String translate(int numberToTranslate) {
        return numberToTranslate % divisor == 0 ? translation : NO_TRANSLATION_VALUE;
    }
}
