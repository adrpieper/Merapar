package merapar.app.controller.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class LocalDateTimeSerializerTest {

    @Test
    public void testSerialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String time = objectMapper.writeValueAsString(new DTOwithTime(LocalDateTime.of(2012,5,15,16,2,12,43000000)));
        assertThat(time).isEqualTo("{\"time\":\"2012-05-15T16:02:12.043+00:00\"}");
    }

    private static class DTOwithTime {
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private final LocalDateTime time;

        private DTOwithTime(LocalDateTime time) {
            this.time = time;
        }

        public LocalDateTime getTime() {
            return time;
        }
    }
}