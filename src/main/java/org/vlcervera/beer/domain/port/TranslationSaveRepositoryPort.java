package org.vlcervera.beer.domain.port;

import java.util.List;


public interface TranslationSaveRepositoryPort {
    void save(List<String> numbersTranslated);
}
