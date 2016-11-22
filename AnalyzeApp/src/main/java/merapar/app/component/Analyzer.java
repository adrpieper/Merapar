package merapar.app.component;

import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.exceptions.BadFileStructure;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

@Component
public class Analyzer {

    private final SAXParserFactory saxParserFactory;

    public Analyzer(SAXParserFactory saxParserFactory) {
        this.saxParserFactory = saxParserFactory;
    }

    public AnalyzeDetailsDTO analyze(InputStream inputStream, PostHandler postHandler) {
        try {
            saxParserFactory.newSAXParser().parse(inputStream, postHandler);
            return  postHandler.getResults();
        } catch (SAXException e) {
            throw new BadFileStructure(e.getCause());
        } catch (IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
