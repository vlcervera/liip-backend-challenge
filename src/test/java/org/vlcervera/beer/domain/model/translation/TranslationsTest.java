package org.vlcervera.beer.domain.model.translation;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.vlcervera.beer.utils.NumbersTranslatedExpected;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TranslationsTest {

    @Test
    public void shouldTranslateAllNumbers() {
        //GIVEN
        DivisorTranslation first  = new DivisorTranslation(3, "fizz");
        DivisorTranslation second = new DivisorTranslation(5, "buzz");

        //SUT
        Translations translations = new Translations(Lists.newArrayList(first, second));

        List<Integer> numbersToTranslate = IntStream.rangeClosed(1, 15)
                                                    .boxed().collect(Collectors.toList());

        //WHEN
        List<String> numbersTranslated = translations.translate(numbersToTranslate);
        //THEN
        Assertions.assertThat(numbersTranslated).isEqualTo(NumbersTranslatedExpected.getNumbersTranslatedFor15());
    }

}