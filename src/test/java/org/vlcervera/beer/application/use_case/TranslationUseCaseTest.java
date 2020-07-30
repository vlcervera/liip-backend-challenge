package org.vlcervera.beer.application.use_case;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vlcervera.beer.domain.model.translation.Translations;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;

import java.util.List;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.verify;

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
        int limit         = 100;
        int numberToStart = 4;

        translationUseCase = new TranslationUseCase(translations, limit, translationSaveRepositoryPort);

        //WHEN
        List<String> numbersTranslated = translationUseCase.translate(numberToStart);

        //THEN
        verify(translations).apply(anyList());

        verify(translationSaveRepositoryPort).save(anyList());

    }

}