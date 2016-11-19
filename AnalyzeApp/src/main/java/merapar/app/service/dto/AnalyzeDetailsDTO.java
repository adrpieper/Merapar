package merapar.app.service.dto;

import java.time.LocalDateTime;

public class AnalyzeDetailsDTO {
    private final LocalDateTime firstPost;
    private final LocalDateTime lastPost;
    private final int totalPosts;
    private final int totalAcceptedPosts;
    private final int avgScore;

    AnalyzeDetailsDTO(LocalDateTime firstPost, LocalDateTime lastPost, int totalPosts, int totalAcceptedPosts, int avgScore) {
        this.firstPost = firstPost;
        this.lastPost = lastPost;
        this.totalPosts = totalPosts;
        this.totalAcceptedPosts = totalAcceptedPosts;
        this.avgScore = avgScore;
    }

    public LocalDateTime getFirstPost() {
        return firstPost;
    }

    public LocalDateTime getLastPost() {
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
