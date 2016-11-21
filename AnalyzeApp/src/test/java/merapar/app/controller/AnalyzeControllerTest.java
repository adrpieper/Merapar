package merapar.app.controller;

import merapar.app.controller.dto.AnalyzeRequestDTO;
import merapar.app.controller.dto.AnalyzeResponseDTO;
import merapar.app.service.AnalyzeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzeControllerTest {

    @Mock
    private AnalyzeService analyzeService;
    @InjectMocks
    private AnalyzeController underTest;

    @Test
    public void should_call_analyzeService_with_url_given_by_request() throws Exception {

        URL givenUrl = new URL("http://anything");
        AnalyzeRequestDTO givenRequest = new AnalyzeRequestDTO(givenUrl);
        AnalyzeResponseDTO expectedResult = new AnalyzeResponseDTO(null, null);
        when(analyzeService.analyzeFile(givenUrl)).thenReturn(expectedResult);

        AnalyzeResponseDTO result = underTest.analyze(givenRequest);

        assertThat(result).isEqualTo(expectedResult);
    }
}