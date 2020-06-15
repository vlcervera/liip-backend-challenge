package org.vlcervera.beer.application.use_case;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vlcervera.beer.domain.exception.NumberToStartIsGreaterThanLimitException;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;
import org.vlcervera.beer.domain.model.translation.Translations;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TranslationUseCaseTest {

    //SUT
    TranslationUseCase translationUseCase;

    @Mock
    Translations translations;

    @Mock
    TranslationSaveRepositoryPort translationSaveRepositoryPort;

    @Test
    public void shouldTranslateAllNumbersAndStoreTheResultAndReturnIt() {
        //GIVEN
        int limit              = 100;
        int numberToStart      = 4;
        int numbersToTranslate = (limit - numberToStart) + 1;

        translationUseCase = new TranslationUseCase(translations, limit, translationSaveRepositoryPort);

        //WHEN
        List<String> numbersTranslated = translationUseCase.translate(numberToStart);

        //THEN
        verify(translations, times(numbersToTranslate)).translate(anyInt());

        verify(translationSaveRepositoryPort, times(1))
                .save(argThat(list -> list.size() == numbersToTranslate));

        Assertions.assertThat(numbersTranslated).hasSize(numbersToTranslate);
    }

    @Test
    public void shouldThrowExceptionWhenNumberToStartIsGreaterThanLimit() {
        org.junit.jupiter.api.Assertions.assertThrows(NumberToStartIsGreaterThanLimitException.class, () -> {
            //GIVEN
            int limit         = 100;
            int numberToStart = 400;

            translationUseCase = new TranslationUseCase(translations, limit, translationSaveRepositoryPort);

            //WHEN
            translationUseCase.translate(numberToStart);
        });


    }
}