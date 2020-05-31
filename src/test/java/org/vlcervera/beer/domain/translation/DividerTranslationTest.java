package org.vlcervera.beer.domain.translation;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DividerTranslationTest {

    private static Stream<Arguments> provideTranslationsCases() {
        final String fizz = "fizz";
        final String buzz = "buzz";

        return Stream.of(
                Arguments.of(3, fizz, 3, fizz),
                Arguments.of(3, fizz, 4, Strings.EMPTY),
                Arguments.of(5, buzz, 1, Strings.EMPTY),
                Arguments.of(5, buzz, 15, buzz)
                        );
    }

    @ParameterizedTest
    @MethodSource("provideTranslationsCases")
    public void execution(int numberBase, String translationBase, int numberToTranslate, String translationExpected) {
        //GIVEN
        DividerTranslation dividerTranslation = new DividerTranslation(numberBase, translationBase);

        //WHEN
        String valueTranslated = dividerTranslation.translate(numberToTranslate);

        //THEN
        assertThat(valueTranslated).isEqualTo(translationExpected);
    }

}