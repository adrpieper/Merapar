package merapar.app.component;

import javafx.geometry.Pos;
import org.junit.Before;
import org.xml.sax.SAXException;
import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.dto.AnalyzeDetailsDTOBuilder;
import merapar.app.service.exceptions.BadFileStructure;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.InputStream;

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
    public void should_return_specified_details() throws Exception {

        AnalyzeDetailsDTO expectedDetailsDTO = new AnalyzeDetailsDTOBuilder().build();
        when(postHandler.getResults()).thenReturn(expectedDetailsDTO);

        AnalyzeDetailsDTO result = underTest.analyze(inputStream, postHandler);

        verify(saxParser).parse(inputStream, postHandler);
        Assertions.assertThat(result).isEqualTo(expectedDetailsDTO);

    }

    @Test(expected = BadFileStructure.class)
    public void should_throw_BadFileStructure_when_parsing_throw_SAXException() throws Exception {


        doThrow(new SAXException()).when(saxParser).parse(inputStream, postHandler);
        underTest.analyze(inputStream, postHandler);
    }
}