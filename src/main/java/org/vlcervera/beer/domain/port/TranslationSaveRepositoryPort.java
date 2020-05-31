package org.vlcervera.beer.domain.port;

import java.util.List;

/**
 * Feature to save the list of numbers translated in a storage system.
 * It can be file system, database or anything storage system
 */
public interface TranslationSaveRepositoryPort {
    void save(List<String> numbersTranslated);
}
