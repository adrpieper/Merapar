import com.jayway.restassured.response.Response;
import merapar.app.Application;
import merapar.app.controller.dto.AnalyzeRequestDTO;
import merapar.app.controller.dto.AnalyzeResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnalyzeIT {

    @Test
    public void testEndPoint() throws Exception {
        Response response = given().when()
                .header("Content-Type","application/json")
                .body(new AnalyzeRequestDTO(new URL("https://s3-eu-west-1.amazonaws.com/merapar-assessment/3dprinting-posts.xml")))
                .post("http://localhost:8080/analyze");

        response.getBody().as(AnalyzeResponseDTO.class);
    }
}
