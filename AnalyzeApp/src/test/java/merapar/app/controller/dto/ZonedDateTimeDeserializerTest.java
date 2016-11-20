package merapar.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import merapar.app.controller.dto.jackson.ZonedDateTimeDeserializer;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ZonedDateTimeDeserializerTest {
    @Test
    public void testDeserialization() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        DTOwithTime dtoWithTime = objectMapper.readValue("{\"time\":\"2012-05-15T16:02:12.043+01:00\"}", DTOwithTime.class);
        assertThat(dtoWithTime.getTime()).isEqualByComparingTo(ZonedDateTime.of(2012,5,15,16,2,12,43000000, ZoneId.of("+01:00")));
    }

    private static class DTOwithTime {
        @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
        private final ZonedDateTime time;

        private DTOwithTime(@JsonProperty("time") ZonedDateTime time) {
            this.time = time;
        }

        public ZonedDateTime getTime() {
            return time;
        }
    }

}