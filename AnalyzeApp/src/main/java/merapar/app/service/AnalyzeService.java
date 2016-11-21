package merapar.app.service;

import merapar.app.component.Analyzer;
import merapar.app.component.PostHandler;
import merapar.app.controller.dto.AnalyzeResponseDTO;
import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.exceptions.UrlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.ZonedDateTime;

@Service
public class AnalyzeService {

    private final Analyzer analyzer;

    @Autowired
    public AnalyzeService(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public AnalyzeResponseDTO analyzeFile(URL fileUrl) {
        try {
            ZonedDateTime analyzeTime = ZonedDateTime.now();
            InputStream inputStream = new BufferedInputStream(fileUrl.openStream());
            PostHandler postHandler = new PostHandler();
            AnalyzeDetailsDTO detailsDTO = analyzer.analyze(inputStream, postHandler);
            return new AnalyzeResponseDTO(analyzeTime, detailsDTO);

        } catch (IOException e) {
            throw new UrlNotFoundException(fileUrl,e);
        }
    }
}
