package org.vlcervera.beer.infrastructure.adapter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vlcervera.beer.domain.exception.TranslationStorageException;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class TranslationSaveRepositoryAdapterTest {

    String path     = "/tmp";
    String fileName = "filename-example.txt";

    @Test
    public void shouldWriteFile() {

        //GIVEN
        FileNameGenerator fileNameGenerator = Mockito.mock(FileNameGenerator.class);

        //SUT
        TranslationSaveRepositoryAdapter translationSaveRepositoryAdapter
                = new TranslationSaveRepositoryAdapter(path, fileNameGenerator);

        Mockito.when(fileNameGenerator.getFileName()).thenReturn(fileName);

        List<String> numbers = IntStream.rangeClosed(1, 100)
                                        .boxed()
                                        .map(Object::toString)
                                        .collect(Collectors.toList());

        //WHEN
        translationSaveRepositoryAdapter.save(numbers);

        //THEN
        Assertions.assertThat(Paths.get(path).resolve(fileName)).exists();
    }

    @Test
    public void shouldThrowDomainExceptionInUnknownAdapterError() {

        org.junit.jupiter.api.Assertions.assertThrows(TranslationStorageException.class, () ->{
            //GIVEN
            FileNameGenerator fileNameGenerator = Mockito.mock(FileNameGenerator.class);

            //SUT
            TranslationSaveRepositoryAdapter translationSaveRepositoryAdapter
                    = new TranslationSaveRepositoryAdapter(path, fileNameGenerator);

            Mockito.when(fileNameGenerator.getFileName()).thenThrow(new IllegalStateException("Unknown error"));

            List<String> numbers = IntStream.rangeClosed(1, 100)
                                            .boxed()
                                            .map(Object::toString)
                                            .collect(Collectors.toList());

            //WHEN
            translationSaveRepositoryAdapter.save(numbers);
        });


    }
}