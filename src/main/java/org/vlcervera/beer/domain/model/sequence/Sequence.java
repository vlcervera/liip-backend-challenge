package org.vlcervera.beer.domain.model.sequence;

import lombok.extern.slf4j.Slf4j;
import org.vlcervera.beer.domain.exception.NumberToStartIsGreaterThanLimitException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Sequence {

    private final int start;
    private final int limit;

    public Sequence(int start, int limit) {
        this.start = start;
        this.limit = limit;
        if (start > limit) {
            log.error("Number to start {} is greater than limit {}", start, limit);
            throw new NumberToStartIsGreaterThanLimitException(start, limit);
        }
    }

    public List<Integer> getSequence() {
        return IntStream.rangeClosed(start, limit)
                        .boxed()
                        .collect(Collectors.toList());

    }
}
