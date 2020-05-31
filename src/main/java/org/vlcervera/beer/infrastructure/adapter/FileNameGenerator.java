package org.vlcervera.beer.infrastructure.adapter;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class FileNameGenerator {
    public String getFileName() {
        String fileBaseName = "beer-datetime.txt";

        UUID fileId = UUID.randomUUID();
        String timeStampForFilename = LocalDateTime.now()
                                                   .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        fileBaseName = fileBaseName.replaceAll("datetime", timeStampForFilename);

        return String.join("-", fileId.toString(), fileBaseName);
    }
}
