package org.vlcervera.beer.infrastructure.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.vlcervera.beer.infrastructure.web.exception_handler.ApiError;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TranslationControllerTest {

    private final ObjectMapper objectMapper = createDefaultMapper();
    @Autowired
    private       MockMvc      mockMvc;

    @Test
    public void shouldReturnListOfNumbersTranslated() throws Exception {
        int    numberToStart = 1;
        String baseEndpoint  = "/translate/" + numberToStart;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                                   .get(baseEndpoint)
                                                   .accept(MediaType.APPLICATION_JSON))
                                  .andDo(MockMvcResultHandlers.print())
                                  .andExpect(status().isOk())
                                  .andReturn();

        List numbers = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);

        //defined in application.yml in test resource
        int limit = 15;
        assertThat(numbers)
                .isNotEmpty()
                .hasSize(limit - numberToStart + 1)
                .isEqualTo(getNumbersTranslatedExpected());
    }


    @Test
    public void shouldReturnErrorLimitExceeded() throws Exception {
        int    numberToStart = 20;
        String baseEndpoint  = "/translate/" + numberToStart;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                                   .get(baseEndpoint)
                                                   .accept(MediaType.APPLICATION_JSON))
                                  .andDo(MockMvcResultHandlers.print())
                                  .andExpect(status().isBadRequest())
                                  .andReturn();


        ApiError apiError = objectMapper.readValue(result.getResponse().getContentAsString(), ApiError.class);

        assertThat(apiError).isNotNull();
        assertThat(apiError.getType()).isEqualTo(ApiError.Type.NUMBER_OF_START_EXCEEDS_LIMIT);

    }


    private List<String> getNumbersTranslatedExpected() {
        return
                Lists.newArrayList(
                        "1",
                        "2",
                        "fizz",
                        "4",
                        "buzz",
                        "fizz",
                        "7",
                        "8",
                        "fizz",
                        "buzz",
                        "11",
                        "fizz",
                        "13",
                        "14",
                        "fizzbuzz"
                                  );
    }

    private ObjectMapper createDefaultMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
