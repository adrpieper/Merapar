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
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@Service
public class AnalyzeService {

    private final SAXParser saxParser;

    @Autowired
    public AnalyzeService(SAXParser saxParser) {
        this.saxParser = saxParser;
    }

    public AnalyzeResponseDTO analyzeFile(URL fileUrl) {
        try {
            ZonedDateTime analyseDate = ZonedDateTime.now();
            Clock.systemDefaultZone().getZone();

            InputStream inputStream = fileUrl.openStream();
            PostHandler postHandler = new PostHandler();
            saxParser.parse(inputStream, postHandler);
            return new AnalyzeResponseDTO(analyseDate, postHandler.getResults());
        } catch (SAXException e) {
            throw new BadFileStructure(e.getCause());
        } catch (IOException e) {
            throw new FileNotFoundException(fileUrl);
        }
    }
}
