package merapar.app.controller.dto;

import merapar.app.service.dto.AnalyzeDetailsDTO;

import java.time.LocalDateTime;

public class AnalyzeResponseDTO {
    private final LocalDateTime analyseDate;
    private final AnalyzeDetailsDTO details;

    public AnalyzeResponseDTO(LocalDateTime analyseDate, AnalyzeDetailsDTO details) {
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
