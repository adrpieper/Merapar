package merapar.app.component;

import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.exceptions.BadFileStructure;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.io.InputStream;

@Component
public class Analyzer {

    private final SAXParser saxParser;

    public Analyzer(SAXParser saxParser) {
        this.saxParser = saxParser;
    }

    public AnalyzeDetailsDTO analyze(InputStream inputStream, PostHandler postHandler) {
        try {
            saxParser.parse(inputStream, postHandler);
            return  postHandler.getResults();
        } catch (SAXException | IOException e) {
            throw new BadFileStructure(e.getCause());
        }
    }
}
