package org.vlcervera.beer.domain.model.translation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DivisorTranslationTest {

    private static Stream<Arguments> provideTranslationsCases() {
        final String fizz = "fizz";
        final String buzz = "buzz";

        return Stream.of(
                Arguments.of(3, fizz, 3, fizz),
                Arguments.of(3, fizz, 4, null),
                Arguments.of(3, fizz, 6, fizz),
                Arguments.of(3, fizz, 30, fizz),
                Arguments.of(3, fizz, 31, null),
                Arguments.of(5, buzz, 1, null),
                Arguments.of(5, buzz, 15, buzz)
                        );
    }

    @ParameterizedTest
    @MethodSource("provideTranslationsCases")
    public void execution(int numberBase, String translationBase, int numberToTranslate, String translationExpected) {
        //GIVEN
        DivisorTranslation divisorTranslation = new DivisorTranslation(numberBase, translationBase);

        //WHEN
        Optional<String> valueTranslated = divisorTranslation.translate(numberToTranslate);

        //THEN
        if (translationExpected != null) {
            assertThat(valueTranslated).isPresent();
            assertThat(valueTranslated.get()).isEqualTo(translationExpected);
        } else {
            assertThat(valueTranslated).isNotPresent();
        }
    }

}