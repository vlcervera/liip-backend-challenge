package org.vlcervera.beer.domain.model.translation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PairNumberTranslationTest {

    private static Stream<Arguments> provideTranslationsCases() {
        final String pair = "pair";

        return Stream.of(
                Arguments.of(10, pair),
                Arguments.of(3, null),
                Arguments.of(5, null),
                Arguments.of(6, pair)
                        );
    }

    @ParameterizedTest
    @MethodSource("provideTranslationsCases")
    public void execution(int numberToTranslate, String translationExpected) {
        //GIVEN
        PairNumberTranslation pairTranslationTest = new PairNumberTranslation();

        //WHEN
        Optional<String> valueTranslated = pairTranslationTest.translate(numberToTranslate);

        //THEN
        if (translationExpected != null) {
            assertThat(valueTranslated).isPresent();
            assertThat(valueTranslated.get()).isEqualTo(translationExpected);
        } else {
            assertThat(valueTranslated).isNotPresent();
        }
    }

}