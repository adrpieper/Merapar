package merapar.app.service.dto;

import java.time.ZonedDateTime;

public class AnalyzeDetailsDTOBuilder {
    private ZonedDateTime firstPost;
    private ZonedDateTime lastPost;
    private int totalPosts;
    private int totalAcceptedPosts;
    private int avgScore;

    public AnalyzeDetailsDTOBuilder withFirstPost(ZonedDateTime firstPost) {
        this.firstPost = firstPost;
        return this;
    }

    public AnalyzeDetailsDTOBuilder withLastPost(ZonedDateTime lastPost) {
        this.lastPost = lastPost;
        return this;
    }

    public AnalyzeDetailsDTOBuilder withTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
        return this;
    }

    public AnalyzeDetailsDTOBuilder withTotalAcceptedPosts(int totalAcceptedPosts) {
        this.totalAcceptedPosts = totalAcceptedPosts;
        return this;
    }

    public AnalyzeDetailsDTOBuilder withAvgScore(int avgScore) {
        this.avgScore = avgScore;
        return this;
    }

    public AnalyzeDetailsDTO build() {
        return new AnalyzeDetailsDTO(firstPost, lastPost, totalPosts, totalAcceptedPosts, avgScore);
    }
}