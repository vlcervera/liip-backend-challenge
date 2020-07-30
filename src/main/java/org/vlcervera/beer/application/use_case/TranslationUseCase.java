package org.vlcervera.beer.application.use_case;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.vlcervera.beer.domain.model.translation.Sequence;
import org.vlcervera.beer.domain.model.translation.Translations;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class TranslationUseCase {

    private final Translations                  translations;
    private final int                           limit;
    private final TranslationSaveRepositoryPort translationSaveRepositoryPort;

    public List<String> translate(int numberToStart) {
        log.info("Start translation use case for numbers from {} to {}", numberToStart, limit);

        Sequence sequence = new Sequence(numberToStart, limit);

        List<String> numbersTranslated = sequence.getSequence().stream()
                                                 .map(translations::translate)
                                                 .collect(Collectors.toList());

        log.info("Translation process returns {} numbers translated", numbersTranslated.size());

        translationSaveRepositoryPort.save(numbersTranslated);

        log.info("Finished translation use case for numbers from {} to {}", numberToStart, limit);
        return numbersTranslated;

    }

}
