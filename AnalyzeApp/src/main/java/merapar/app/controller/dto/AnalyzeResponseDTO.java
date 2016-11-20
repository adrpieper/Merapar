package merapar.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import merapar.app.controller.dto.jackson.ZonedDateTimeDeserializer;
import merapar.app.controller.dto.jackson.ZonedDateTimeSerializer;
import merapar.app.service.dto.AnalyzeDetailsDTO;

import java.time.ZonedDateTime;

public class AnalyzeResponseDTO {
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private final ZonedDateTime analyseDate;
    private final AnalyzeDetailsDTO details;

    public AnalyzeResponseDTO(
            @JsonProperty("analyseDate") ZonedDateTime analyseDate,
            @JsonProperty("details") AnalyzeDetailsDTO details) {
        this.analyseDate = analyseDate;
        this.details = details;
    }

    public ZonedDateTime getAnalyseDate() {
        return analyseDate;
    }

    public AnalyzeDetailsDTO getDetails() {
        return details;
    }
}
