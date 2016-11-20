package merapar.app.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import merapar.app.controller.dto.jackson.ZonedDateTimeDeserializer;
import merapar.app.controller.dto.jackson.ZonedDateTimeSerializer;

import java.time.ZonedDateTime;

public class AnalyzeDetailsDTO {
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private final ZonedDateTime firstPost;
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private final ZonedDateTime lastPost;
    private final int totalPosts;
    private final int totalAcceptedPosts;
    private final int avgScore;

    AnalyzeDetailsDTO(
            @JsonProperty("firstPost") ZonedDateTime firstPost,
            @JsonProperty("lastPost") ZonedDateTime lastPost,
            @JsonProperty("totalPosts") int totalPosts,
            @JsonProperty("totalAcceptedPosts") int totalAcceptedPosts,
            @JsonProperty("avgScore") int avgScore) {
        this.firstPost = firstPost;
        this.lastPost = lastPost;
        this.totalPosts = totalPosts;
        this.totalAcceptedPosts = totalAcceptedPosts;
        this.avgScore = avgScore;
    }

    public ZonedDateTime getFirstPost() {
        return firstPost;
    }

    public ZonedDateTime getLastPost() {
        return lastPost;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public int getTotalAcceptedPosts() {
        return totalAcceptedPosts;
    }

    public int getAvgScore() {
        return avgScore;
    }

    @Override
    public String toString() {
        return "AnalyzeDetailsDTO{" +
                "firstPost=" + firstPost +
                ", lastPost=" + lastPost +
                ", totalPosts=" + totalPosts +
                ", totalAcceptedPosts=" + totalAcceptedPosts +
                ", avgScore=" + avgScore +
                '}';
    }
}
