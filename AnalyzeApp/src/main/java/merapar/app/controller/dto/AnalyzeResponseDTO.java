package merapar.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import merapar.app.service.dto.AnalyzeDetailsDTO;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class AnalyzeResponseDTO {
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
