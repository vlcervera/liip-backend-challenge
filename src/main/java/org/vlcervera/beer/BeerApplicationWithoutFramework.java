package org.vlcervera.beer;

import org.vlcervera.beer.application.use_case.TranslationUseCase;
import org.vlcervera.beer.domain.model.translation.DividerTranslation;
import org.vlcervera.beer.domain.model.translation.Translation;
import org.vlcervera.beer.domain.model.translation.Translations;
import org.vlcervera.beer.infrastructure.adapter.FileNameGenerator;
import org.vlcervera.beer.infrastructure.adapter.TranslationSaveRepositoryAdapter;

import java.util.List;

/**
 * This application can be runned without be integrated with Spring framework.
 * It can be launched creating all dependencies required and inject them in the main classes
 */
public class BeerApplicationWithoutFramework {

    public static void main(String[] args) {

        /* Create beans for dependencies */

        // For TranslationSaveRepositoryAdapter
        FileNameGenerator                fileNameGenerator                = new FileNameGenerator();
        String                           path                             = "/tmp/test";
        TranslationSaveRepositoryAdapter translationSaveRepositoryAdapter = new TranslationSaveRepositoryAdapter(path, fileNameGenerator);

        // Create translations available
        DividerTranslation customDividerTranslationForFizz = new DividerTranslation(3, "fizz");
        DividerTranslation customDividerTranslationForBuzz = new DividerTranslation(5, "buzz");

        List<Translation> translationsToUse = List.of(customDividerTranslationForFizz, customDividerTranslationForBuzz);
        Translations      translations      = new Translations(translationsToUse);

        //Use case
        int                limit              = 100;
        TranslationUseCase translationUseCase = new TranslationUseCase(translations, limit, translationSaveRepositoryAdapter);


        int numberToStart = 10;
        //Execute use case
        List<String> numbersTranslated = translationUseCase.translate(numberToStart);

        numbersTranslated.forEach(System.out::println);

    }
}
