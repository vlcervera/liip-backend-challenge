package org.vlcervera.beer.infrastructure.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vlcervera.beer.application.use_case.TranslationUseCase;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;
import org.vlcervera.beer.domain.model.translation.DividerTranslation;
import org.vlcervera.beer.domain.model.translation.Translation;
import org.vlcervera.beer.domain.model.translation.Translations;
import org.vlcervera.beer.infrastructure.adapter.FileNameGenerator;
import org.vlcervera.beer.infrastructure.adapter.TranslationSaveRepositoryAdapter;

import java.util.List;

@Configuration
public class BeanConfig {

    @Value("${beer.limit}")
    private int limit;

    @Value("${beer.path}")
    private String path;

    @Bean
    public TranslationSaveRepositoryPort getTranslationSavedRepository(FileNameGenerator fileNameGenerator) {
        return new TranslationSaveRepositoryAdapter(path, fileNameGenerator);
    }

    @Bean
    public TranslationUseCase getTranslationUseCase(TranslationSaveRepositoryPort translationSaveRepositoryPort) {

        DividerTranslation customDividerTranslationForFizz = new DividerTranslation(3, "fizz");
        DividerTranslation customDividerTranslationForBuzz = new DividerTranslation(5, "buzz");
        /*
        TODO if you want to add more translators to the use case you can create new implementations of Translation interface.
        For example,
        PairNumberTranslation pair         = new PairNumberTranslation();

        ALl this translations will be applied in use case
         */

        List<Translation> translationsToUse = Lists.newArrayList(customDividerTranslationForFizz, customDividerTranslationForBuzz);
        Translations      translations      = new Translations(translationsToUse);

        return new TranslationUseCase(translations, limit, translationSaveRepositoryPort);
    }

}
