package merapar.app.controller.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import merapar.app.controller.dto.jackson.ZonedDateTimeSerializer;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ZonedDateTimeSerializerTest {

    @Test
    public void testSerialization() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String time = objectMapper.writeValueAsString(new DTOwithTime(ZonedDateTime.of(2012,5,15,16,2,12,43000000, ZoneId.of("+01:00"))));
        assertThat(time).isEqualTo("{\"time\":\"2012-05-15T16:02:12.043+01:00\"}");
    }

    private static class DTOwithTime {
        @JsonSerialize(using = ZonedDateTimeSerializer.class)
        private final ZonedDateTime time;

        private DTOwithTime(ZonedDateTime time) {
            this.time = time;
        }

        public ZonedDateTime getTime() {
            return time;
        }
    }
}