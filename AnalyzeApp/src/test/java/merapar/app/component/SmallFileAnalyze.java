package merapar.app.component;

import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.dto.AnalyzeDetailsDTOBuilder;

import java.net.URL;
import java.time.Clock;
import java.time.ZonedDateTime;

public class SmallFileAnalyze {

    public static AnalyzeDetailsDTO getDetails() {
        return new AnalyzeDetailsDTOBuilder()
                .withAvgScore(20/8)
                .withFirstPost(ZonedDateTime.of(2015,7,14,18,39,27,757000000, Clock.systemUTC().getZone()))
                .withLastPost(ZonedDateTime.of(2015,8,14,22,2,58,73000000, Clock.systemUTC().getZone()))
                .withTotalPosts(8)
                .build();
    }

    public static URL getURL(){
        return ClassLoader.getSystemClassLoader().getResource("__files/small-file.xml");
    }
}
