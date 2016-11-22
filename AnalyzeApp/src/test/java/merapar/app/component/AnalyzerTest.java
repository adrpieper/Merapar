package merapar.app.component;

import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.dto.AnalyzeDetailsDTOBuilder;
import merapar.app.service.exceptions.BadFileStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerTest {

    @Mock
    private SAXParserFactory saxParserFactory;

    @Mock
    private SAXParser saxParser;

    @Mock
    private InputStream inputStream;

    @Mock
    private PostHandler postHandler;

    @InjectMocks
    private Analyzer underTest;

    @Before
    public void setUp() throws Exception {
        when(saxParserFactory.newSAXParser()).thenReturn(saxParser);
    }

    @Test
    public void should_return_expected_details() throws Exception {

        AnalyzeDetailsDTO expectedDetailsDTO = new AnalyzeDetailsDTOBuilder().build();
        when(postHandler.getResults()).thenReturn(expectedDetailsDTO);

        AnalyzeDetailsDTO result = underTest.analyze(inputStream, postHandler);

        verify(saxParser).parse(inputStream, postHandler);
        assertThat(result).isEqualTo(expectedDetailsDTO);

    }

    @Test(expected = BadFileStructure.class)
    public void should_throw_BadFileStructure_when_parsing_throw_SAXException() throws Exception {


        doThrow(new SAXException()).when(saxParser).parse(inputStream, postHandler);
        underTest.analyze(inputStream, postHandler);
    }
}