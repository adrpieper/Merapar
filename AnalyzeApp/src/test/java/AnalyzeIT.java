import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.jayway.restassured.response.Response;
import merapar.app.Application;
import merapar.app.component.SmallFileAnalyze;
import merapar.app.controller.dto.AnalyzeRequestDTO;
import merapar.app.controller.dto.AnalyzeResponseDTO;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.time.ZonedDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnalyzeIT {

    @Rule
    public WireMockRule fileMock = new WireMockRule(8081);

    @Test
    public void should_analyze_real_file() throws Exception {

        mockFile("real-file.xml");

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(new AnalyzeRequestDTO(new URL("http://localhost:8081/real-file.xml")))
                .post("/analyze");

        assertThat(response.getStatusCode()).isEqualTo(OK.value());
        response.getBody().as(AnalyzeResponseDTO.class);
    }

    @Test
    public void should_analyze_small_file_and_return_specified_result() throws Exception {

        mockFile("small-file.xml");

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(new AnalyzeRequestDTO(new URL("http://localhost:8081/small-file.xml")))
                .post("/analyze");

        assertThat(response.getStatusCode()).isEqualTo(OK.value());
        AnalyzeResponseDTO responseDTO = response.getBody().as(AnalyzeResponseDTO.class);
        assertThat(responseDTO.getAnalyseDate()).isBetween(ZonedDateTime.now().minusHours(1), ZonedDateTime.now());
        assertThat(responseDTO.getDetails()).isEqualToComparingFieldByField(SmallFileAnalyze.getDetails());
    }

    @Test
    public void should_return_bad_request_when_url_is_bad() throws Exception
    {
        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(new JSONObject().put("url","not_a_url").toString())
                .post("/analyze");

        assertThat(response.getStatusCode()).isEqualTo(BAD_REQUEST.value());
    }

    private void mockFile(String fileName) {
        fileMock.stubFor(
                get(urlEqualTo("/" + fileName))
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", "text/xml")
                                        .withBodyFile(fileName)
                        )
        );
    }
}
