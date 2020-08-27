package org.vlcervera.beer.domain.model.sequence;

import org.junit.jupiter.api.Test;
import org.vlcervera.beer.domain.exception.NumberToStartIsGreaterThanLimitException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SequenceTest {

    @Test
    public void shouldRetrieveCorrectSequence() {
        //GIVEN
        int limit                  = 100;
        int start                  = 4;
        int sequenceLenghtExpected = (limit - start) + 1;

        Sequence sequence = new Sequence(start, limit);

        //WHEN
        List<Integer> sequenceNumbers = sequence.getSequence();

        //THEN
        assertThat(sequenceNumbers).hasSize(sequenceLenghtExpected);
        assertThat(sequenceNumbers.stream()
                                  .mapToInt(v -> v)
                                  .min()
                                  .getAsInt()).isEqualTo(start);

        assertThat(sequenceNumbers.stream()
                                  .mapToInt(v -> v)
                                  .max()
                                  .getAsInt()).isEqualTo(limit);
    }

    @Test
    public void shouldThrowExceptionWhenNumberToStartIsGreaterThanLimit() {

        //THEN
        assertThrows(NumberToStartIsGreaterThanLimitException.class, () -> {
            //GIVEN
            int limit         = 100;
            int numberToStart = 400;

            //WHEN
            Sequence sequence = new Sequence(numberToStart, limit);

        });


    }
}