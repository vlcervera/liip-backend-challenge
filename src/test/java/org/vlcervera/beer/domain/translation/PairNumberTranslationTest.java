package org.vlcervera.beer.domain.translation;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PairNumberTranslationTest {

    private static Stream<Arguments> provideTranslationsCases() {
        final String pair = "pair";

        return Stream.of(
                Arguments.of(10, pair),
                Arguments.of(3, Strings.EMPTY),
                Arguments.of(5, Strings.EMPTY),
                Arguments.of(6, pair)
                        );
    }

    @ParameterizedTest
    @MethodSource("provideTranslationsCases")
    public void execution(int numberToTranslate, String translationExpected) {
        //GIVEN
        PairNumberTranslation pairTranslationTest = new PairNumberTranslation();

        //WHEN
        String valueTranslated = pairTranslationTest.translate(numberToTranslate);

        //THEN
        assertThat(valueTranslated).isEqualTo(translationExpected);
    }

}