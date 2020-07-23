package org.vlcervera.beer.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.vlcervera.beer.domain.exception.TranslationStorageException;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class TranslationSaveRepositoryAdapter implements TranslationSaveRepositoryPort {

    private final String            path;
    private final FileNameGenerator fileNameGenerator;

    @Override
    public void save(List<String> numbersTranslated) {
        log.info("Start storage of {} numbers translated in local file", numbersTranslated.size());
        try {
            Path directory = Paths.get(path);
            Files.createDirectories(directory);

            String fileName = fileNameGenerator.getFileName();
            log.info("File name is {}", fileName);
            Path out = directory.resolve(fileName);

            Files.write(out, numbersTranslated, Charset.defaultCharset());
            log.info("Finished storage of {} numbers translated in local file success", numbersTranslated.size());

        } catch (Exception e) {
            log.error("Error in file storage", e);
            throw new TranslationStorageException(e);
        }
    }

}
