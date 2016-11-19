package merapar.app.service;

import merapar.app.component.PostHandler;
import merapar.app.controller.dto.AnalyzeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;

@Service
public class AnalyzeService {

    private final SAXParser saxParser;
    private final PostHandler postHandler;

    @Autowired
    public AnalyzeService(SAXParser saxParser, PostHandler postHandler) {
        this.saxParser = saxParser;
        this.postHandler = postHandler;
    }

    public AnalyzeResponseDTO analyzeFile(URL fileUrl) {
        try {
            LocalDateTime analyseDate = LocalDateTime.now();
            InputStream inputStream = fileUrl.openStream();
            saxParser.parse(inputStream, postHandler);
            return new AnalyzeResponseDTO(analyseDate, postHandler.getResults());
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
