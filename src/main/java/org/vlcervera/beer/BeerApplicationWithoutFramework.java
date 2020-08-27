package org.vlcervera.beer;

import org.vlcervera.beer.application.use_case.TranslationUseCase;
import org.vlcervera.beer.domain.model.translation.DivisorTranslation;
import org.vlcervera.beer.domain.model.translation.Translation;
import org.vlcervera.beer.domain.model.translation.Translations;
import org.vlcervera.beer.infrastructure.adapter.FileNameGenerator;
import org.vlcervera.beer.infrastructure.adapter.TranslationStorageRepositoryAdapter;

import java.util.List;

/**
 * This application can be runned without be integrated with Spring framework.
 * It can be launched creating all dependencies required and inject them in the main classes
 */
public class BeerApplicationWithoutFramework {

    public static void main(String[] args) {

        FileNameGenerator                   fileNameGenerator                   = new FileNameGenerator();
        String                              path                                = "/tmp/test";
        TranslationStorageRepositoryAdapter translationStorageRepositoryAdapter = new TranslationStorageRepositoryAdapter(path, fileNameGenerator);

        DivisorTranslation customDivisorTranslationForFizz = new DivisorTranslation(3, "fizz");
        DivisorTranslation customDivisorTranslationForBuzz = new DivisorTranslation(5, "buzz");


        List<Translation> translationsToUse = List.of(customDivisorTranslationForFizz, customDivisorTranslationForBuzz);
        Translations      translations      = new Translations(translationsToUse);

        int                limit              = 100;
        TranslationUseCase translationUseCase = new TranslationUseCase(translations, limit, translationStorageRepositoryAdapter);


        int          numberToStart     = 10;
        List<String> numbersTranslated = translationUseCase.translate(numberToStart);

        numbersTranslated.forEach(System.out::println);

    }
}
