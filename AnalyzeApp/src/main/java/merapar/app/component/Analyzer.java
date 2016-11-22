package merapar.app.component;

import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.exceptions.BadFileStructure;
import merapar.app.service.exceptions.UrlNotFoundException;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

@Component
public class Analyzer {

    private final SAXParserFactory saxParserFactory;

    public Analyzer(SAXParserFactory saxParserFactory) {
        this.saxParserFactory = saxParserFactory;
    }

    public AnalyzeDetailsDTO analyze(InputStreamSupplier inputStream, PostHandler postHandler) throws IOException {
        try {
            saxParserFactory.newSAXParser().parse(inputStream.openStream(), postHandler);
            return  postHandler.getResults();
        } catch (SAXException e) {
            throw new BadFileStructure(e.getCause());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public interface InputStreamSupplier {
        InputStream openStream() throws IOException;
    }
}
