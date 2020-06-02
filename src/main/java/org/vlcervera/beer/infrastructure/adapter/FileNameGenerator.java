package org.vlcervera.beer.infrastructure.adapter;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class FileNameGenerator {
    public String getFileName() {
        String fileBaseName = "%s-beer-%s.txt";
        UUID fileId = UUID.randomUUID();
        String timeStampForFilename = LocalDateTime.now()
                                                   .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        return String.format(fileBaseName, fileId.toString(), timeStampForFilename);
    }

}
