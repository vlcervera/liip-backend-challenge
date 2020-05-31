package org.vlcervera.beer.infrastructure.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.vlcervera.beer.application.use_case.TranslationUseCase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TranslationController {

    private final TranslationUseCase translationUseCase;

    @GetMapping("/translate/{numberToStart}")
    public List<String> getTranslations(@PathVariable("numberToStart") int numberToStart) {
        log.info("Receive translate request with numberToStart {}", numberToStart);
        return translationUseCase.translate(numberToStart);
    }

}
