package org.vlcervera.beer.domain.model.translation;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.util.Strings.EMPTY;

/**
 * Translations bean has the behavior to bind the application of various translation rules.
 * Each Translation instance has the method translate with it own way to translate a number.
 * <p>
 * In case of doesn't apply no one rule for a number the return of this method will be the
 * number in string value
 */
@RequiredArgsConstructor
public class Translations {
    private final List<Translation> translations;

    public List<String> apply(List<Integer> numbersToTranslate) {
        return numbersToTranslate.stream()
                                 .map(this::apply)
                                 .collect(Collectors.toList());

    }

    public String apply(int numberToTranslate) {

        /* Apply all translations to numberToTranslate */
        List<String> translationExecuted = translations.stream()
                                                       .map(translation -> translation.translate(numberToTranslate))
                                                       .filter(Optional::isPresent)
                                                       .map(Optional::get)
                                                       .collect(toList());

        /* Check if no one translation has been applied to numberToTranslate */
        boolean numberHasNotBeenTranslated = translationExecuted.isEmpty();

        /* In case of no one translation has been applied the method will return the number in string format
        If any translation has been applied the result of this method is the join of all values translated. */
        return numberHasNotBeenTranslated
                ? String.valueOf(numberToTranslate)
                : String.join(EMPTY, translationExecuted);

    }


}
