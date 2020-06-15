package org.vlcervera.beer.application.use_case;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.vlcervera.beer.domain.exception.NumberToStartIsGreaterThanLimitException;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;
import org.vlcervera.beer.domain.model.translation.Translations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
public class TranslationUseCase {

    private final Translations                  translations;
    private final int                           limit;
    private final TranslationSaveRepositoryPort translationSaveRepositoryPort;

    public List<String> translate(int numberToStart) {
        log.info("Start translation use case for numbers from {} to {}", numberToStart, limit);

        this.checkNumberToStartAndLimit(numberToStart, limit);

        List<String> numbersTranslated = IntStream.rangeClosed(numberToStart, limit)
                                                  .boxed()
                                                  .map(translations::translate)
                                                  .collect(Collectors.toList());

        log.info("Translation process returns {} numbers translated", numbersTranslated.size());

        translationSaveRepositoryPort.save(numbersTranslated);

        log.info("Finished translation use case for numbers from {} to {}", numberToStart, limit);
        return numbersTranslated;

    }

    private void checkNumberToStartAndLimit(int numberToStart, int limit) {
        if (numberToStart > limit) {
            log.error("Number to start {} is greater than limit {}", numberToStart, limit);
            throw new NumberToStartIsGreaterThanLimitException(numberToStart, limit);
        }
    }

}
