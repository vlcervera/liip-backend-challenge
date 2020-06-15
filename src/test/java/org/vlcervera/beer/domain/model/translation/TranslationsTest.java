package org.vlcervera.beer.domain.model.translation;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.vlcervera.beer.utils.NumbersTranslatedExpected;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TranslationsTest {

    private Translations translations;

    @Test
    public void shouldTranslateAllNumbers() {
        //GIVEN
        DivisorTranslation first  = new DivisorTranslation(3, "fizz");
        DivisorTranslation second = new DivisorTranslation(5, "buzz");

        translations = new Translations(Lists.newArrayList(first, second));

        List<Integer> numbersToTranslate = IntStream.rangeClosed(1, 15)
                                                    .boxed().collect(Collectors.toList());

        //WHEN
        List<String> numbersTranslated = numbersToTranslate.stream()
                                                           .map(translations::translate)
                                                           .collect(Collectors.toList());

        //THEN
        Assertions.assertThat(numbersTranslated).isEqualTo(NumbersTranslatedExpected.getNumbersTranslatedFor15());
    }

}