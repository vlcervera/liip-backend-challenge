package org.vlcervera.beer.domain.model.translation;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.util.Strings.EMPTY;

@RequiredArgsConstructor
public class Translations {
    private final List<Translation> translations;

    public List<String> apply(List<Integer> numbersToTranslate) {
        return numbersToTranslate.stream()
                                 .map(this::apply)
                                 .collect(Collectors.toList());

    }

    public String apply(int numberToTranslate) {

        List<String> translationExecuted = translations.stream()
                                                       .map(translation -> translation.translate(numberToTranslate))
                                                       .filter(Optional::isPresent)
                                                       .map(Optional::get)
                                                       .collect(toList());

        boolean numberHasNotBeenTranslated = translationExecuted.isEmpty();


        return numberHasNotBeenTranslated
                ? String.valueOf(numberToTranslate)
                : String.join(EMPTY, translationExecuted);

    }


}
