package merapar.app.service;

import merapar.app.component.Analyzer;
import merapar.app.component.PostHandler;
import merapar.app.component.SmallFileAnalyze;
import merapar.app.controller.dto.AnalyzeResponseDTO;
import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.exceptions.UrlNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzeServiceTest {

    @Mock
    private Analyzer analyzer;

    @Mock
    private InputStream inputStream;

    @Captor
    private ArgumentCaptor<BufferedInputStream> bufferedInputStreamArgumentCaptor;

    @InjectMocks
    private AnalyzeService underTest;

    @Test
    public void should_analyze_without_exceptions () throws Exception {

        AnalyzeDetailsDTO expectedDetails = SmallFileAnalyze.getDetails();
        when(analyzer.analyze( isA(Analyzer.InputStreamSupplier.class), isA(PostHandler.class))).thenReturn(expectedDetails);

        AnalyzeResponseDTO analyzeResponseDTO = underTest.analyzeFile(new URL("http://mockURL"));

        assertThat(analyzeResponseDTO.getDetails()).isEqualTo(expectedDetails);
        assertThat(analyzeResponseDTO.getAnalyseDate()).isBetween(ZonedDateTime.now().minusMinutes(5),ZonedDateTime.now());
    }

    @Test(expected = UrlNotFoundException.class)
    public void should_throw_file_not_found_when_url_cant_open () throws Exception {

        when(analyzer.analyze( isA(Analyzer.InputStreamSupplier.class), isA(PostHandler.class))).thenThrow(IOException.class);
        underTest.analyzeFile(new URL("http://not_a_url"));
    }
}