package merapar.app.component;

import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.dto.AnalyzeDetailsDTOBuilder;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class PostHandler extends DefaultHandler {
    private long scoreSum;
    private int rows;
    private int acceptedPosts;
    private LocalDateTime firstPost;
    private LocalDateTime lastPost;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        scoreSum = 0;
        rows = 0;
        acceptedPosts = 0;
        firstPost = LocalDateTime.MAX;
        lastPost = LocalDateTime.MIN;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equalsIgnoreCase("row")) {
                rows ++;
                scoreSum += Integer.parseInt(attributes.getValue("Score"));

                if (attributes.getIndex("AcceptedAnswerId") != -1) {
                    acceptedPosts++;
                }
                analyzeCreationDate(attributes.getValue("CreationDate"));
            }
        }catch (Exception e){
            throw new SAXException(e);
        }

    }

    private void analyzeCreationDate(String s) {
        LocalDateTime creationDate = LocalDateTime.parse(s);
        if (creationDate.isBefore(firstPost)) {
            firstPost = creationDate;
        }
        if (creationDate.isAfter(lastPost)) {
            lastPost= creationDate;
        }
    }

    public AnalyzeDetailsDTO getResults() {
        return new AnalyzeDetailsDTOBuilder()
                .withAvgScore(getAvgScore())
                .withFirstPost(toZonedDT(firstPost))
                .withLastPost(toZonedDT(lastPost))
                .withTotalAcceptedPosts(acceptedPosts)
                .withTotalPosts(rows)
                .build();
    }

    private ZonedDateTime toZonedDT(LocalDateTime localDateTime) {
        return ZonedDateTime.ofInstant(localDateTime, ZoneOffset.UTC, Clock.systemUTC().getZone());
    }

    private int getAvgScore() {
        if (rows == 0) {
            return 0;
        }
        return (int) (scoreSum/rows);
    }
}
