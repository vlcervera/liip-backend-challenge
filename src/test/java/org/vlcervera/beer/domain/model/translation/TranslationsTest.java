package org.vlcervera.beer.domain.model.translation;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TranslationsTest {

    @Test
    public void shouldTranslateAllNumbers() {
        //GIVEN

        //Create translation implementation using lambdas -> Translation is a Functional Interface
        Translation translationByThree = getTranslationDivisorImplementation(3, "fizz");
        Translation translationByFive  = getTranslationDivisorImplementation(5, "buzz");
        Translation translationTen     = getTranslationDivisorImplementation(10, "ten");

        List<Translation> translationsToApply = Lists.newArrayList(translationByThree, translationByFive, translationTen);
        //SUT
        Translations translations = new Translations(translationsToApply);

        List<Integer> numbersToTranslate = IntStream.rangeClosed(1, 15)
                                                    .boxed().collect(Collectors.toList());

        //WHEN
        List<String> numbersTranslated = translations.apply(numbersToTranslate);
        //THEN
        Assertions.assertThat(numbersTranslated).isEqualTo(getNumbersTranslatedFor15());
    }

    private Translation getTranslationDivisorImplementation(int base, String translation) {
        return number -> number % base == 0 ? Optional.of(translation) : Optional.empty();
    }

    public List<String> getNumbersTranslatedFor15() {
        return
                Lists.newArrayList(
                        "1",
                        "2",
                        "fizz",
                        "4",
                        "buzz",
                        "fizz",
                        "7",
                        "8",
                        "fizz",
                        "buzzten",
                        "11",
                        "fizz",
                        "13",
                        "14",
                        "fizzbuzz"
                                  );
    }

}