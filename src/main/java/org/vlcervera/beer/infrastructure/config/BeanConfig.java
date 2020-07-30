package org.vlcervera.beer.infrastructure.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vlcervera.beer.application.use_case.TranslationUseCase;
import org.vlcervera.beer.domain.model.translation.DivisorTranslation;
import org.vlcervera.beer.domain.model.translation.Translation;
import org.vlcervera.beer.domain.model.translation.Translations;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;
import org.vlcervera.beer.infrastructure.adapter.FileNameGenerator;
import org.vlcervera.beer.infrastructure.adapter.TranslationStorageRepositoryAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@Slf4j
public class BeanConfig {

    @Value("${beer.limit}")
    private int limit;

    @Value("${beer.path}")
    private String path;

    @PostConstruct
    public void logConfiguration() {
        log.info("Beer application configuration");
        log.info("Translation sequence limit........ {}", limit);
        log.info("Translation storage path.......... {}", path);
    }

    @Bean
    public TranslationSaveRepositoryPort getTranslationSavedRepository(FileNameGenerator fileNameGenerator) {
        return new TranslationStorageRepositoryAdapter(path, fileNameGenerator);
    }

    @Bean
    public TranslationUseCase getTranslationUseCase(TranslationSaveRepositoryPort translationSaveRepositoryPort) {

        DivisorTranslation customDivisorTranslationForFizz = new DivisorTranslation(3, "fizz");
        DivisorTranslation customDivisorTranslationForBuzz = new DivisorTranslation(5, "buzz");
        /*if you want to add more translators to the use case you can create new implementations of Translation interface.
        For example,
        PairNumberTranslation pair         = new PairNumberTranslation();

        Also, we can create a new implementation of Translation using lambdas because it is a Functional interface:
        Translation translationTen     = number -> number % 10 == 0 ? Optional.of("ten") : Optional.empty();

        ALl this translations will be applied in use case*/

        List<Translation> translationsToUse = Lists.newArrayList(customDivisorTranslationForFizz, customDivisorTranslationForBuzz);
        Translations      translations      = new Translations(translationsToUse);

        return new TranslationUseCase(translations, limit, translationSaveRepositoryPort);
    }

}
