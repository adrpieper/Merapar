package merapar.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import merapar.app.service.dto.AnalyzeDetailsDTO;

import java.time.LocalDateTime;

public class AnalyzeResponseDTO {
    private final LocalDateTime analyseDate;
    private final AnalyzeDetailsDTO details;

    public AnalyzeResponseDTO(
            @JsonProperty("analyseDate") LocalDateTime analyseDate,
            @JsonProperty("details") AnalyzeDetailsDTO details) {
        this.analyseDate = analyseDate;
        this.details = details;
    }

    public LocalDateTime getAnalyseDate() {
        return analyseDate;
    }

    public AnalyzeDetailsDTO getDetails() {
        return details;
    }
}
