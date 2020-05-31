package org.vlcervera.beer.domain.translation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DividerTranslation implements Translation {
    private final int    divider;
    private final String translation;

    public String translate(int numberToTranslate) {
        return numberToTranslate % divider == 0 ? translation : NO_TRANSLATION_VALUE;
    }
}
