package merapar.app.component;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PostHandlerTest {
    private PostHandler underTest = new PostHandler();



    @Test
    public void should_parse_small_file_and_return_specific_results() throws Exception {

        parseFile("small-file.xml");
        assertThat(underTest.getResults()).isEqualToComparingFieldByField(SmallFileAnalyze.getDetails());

    }

    @Test
    public void should_parse_real_file_without_exceptions() throws Exception {

        parseFile("real-file.xml");
        System.out.println(underTest.getResults());

    }

    private void parseFile(String fileName) throws Exception {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResource(fileName).openStream();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(inputStream, underTest);
    }
}
