package org.vlcervera.beer.domain.translation;

import lombok.RequiredArgsConstructor;

import java.util.List;

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

    public String translate(int numberToTranslate) {

        /* Apply all translations to numberToTranslate */
        List<String> translationExecuted = translations.stream()
                                                       .map(dividerTranslation -> dividerTranslation.translate(numberToTranslate))
                                                       .collect(toList());

        /* Check if no one translation has been applied to numberToTranslate */
        boolean numberHasNotBeenTranslated = translationExecuted.stream()
                                                                .allMatch(this::numberNotTranslated);
        /*
        In case of any translation has been applied the result of this method is the join of all values translated.
        If no one translation has been applied the method will return the number in string format
         */
        return numberHasNotBeenTranslated
                ? String.valueOf(numberToTranslate)
                : String.join(EMPTY, translationExecuted);

    }

    private boolean numberNotTranslated(String translation) {
        return translation.equals(Translation.NO_TRANSLATION_VALUE);
    }

}
