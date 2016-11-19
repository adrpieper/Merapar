import merapar.app.component.PostHandler;
import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.dto.AnalyzeDetailsDTOBuilder;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class PostHandlerTest {
    private PostHandler underTest = new PostHandler();

    private static final AnalyzeDetailsDTO SMALL_FILE_DETAILS = new AnalyzeDetailsDTOBuilder()
            .withAvgScore(20/8)
            .withFirstPost(LocalDateTime.of(2015,7,14,18,39,27,757000000))
            .withLastPost(LocalDateTime.of(2015,8,14,22,2,58,73000000))
            .withTotalPosts(8)
            .build();

    @Test
    public void should_parse_small_file_and_return_specific_results() throws Exception {

        parseFile("small-file.xml");
        assertThat(underTest.getResults()).isEqualToComparingFieldByField(SMALL_FILE_DETAILS);

    }

    @Test
    public void should_parse_real_file_without_exceptions() throws Exception {

        parseFile("real-file.xml");
        System.out.println(underTest.getResults());

    }

    private void parseFile(String fileName) throws IOException, ParserConfigurationException, SAXException {
        InputStream inputStream = PostHandlerTest.class.getResource(fileName).openStream();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(inputStream, underTest);
    }
}
