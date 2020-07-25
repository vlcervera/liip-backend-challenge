package org.vlcervera.beer.infrastructure.adapter;

import lombok.extern.slf4j.Slf4j;
import org.vlcervera.beer.domain.exception.TranslationStorageException;
import org.vlcervera.beer.domain.port.TranslationSaveRepositoryPort;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class TranslationStorageRepositoryAdapter implements TranslationSaveRepositoryPort {

    private final Path              path;
    private final FileNameGenerator fileNameGenerator;


    public TranslationStorageRepositoryAdapter(String path, FileNameGenerator fileNameGenerator) {
        this.path              = Paths.get(path);
        this.fileNameGenerator = fileNameGenerator;
        try {
            Files.createDirectories(this.path);
        } catch (IOException e) {
            log.error("Error in path creation for translation storage", e);
            throw new TranslationStorageException(e);
        }
    }

    @Override
    public void save(List<String> numbersTranslated) {
        log.info("Start storage of {} numbers translated in local file", numbersTranslated.size());
        try {
            String fileName = fileNameGenerator.getFileName();
            log.info("File name is {}", fileName);
            Path output = path.resolve(fileName);

            Files.write(output, numbersTranslated, Charset.defaultCharset());
            log.info("Finished storage of {} numbers translated in local file success", numbersTranslated.size());

        } catch (Exception e) {
            log.error("Error in file storage", e);
            throw new TranslationStorageException(e);
        }
    }

}
