package merapar.app.component;

import merapar.app.service.dto.AnalyzeDetailsDTO;
import merapar.app.service.dto.AnalyzeDetailsDTOBuilder;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;

@Component
public class PostHandler extends DefaultHandler {
    private long scoreSum;
    private int rows;
    private LocalDateTime firstPost;
    private LocalDateTime lastPost;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        scoreSum = 0;
        rows = 0;
        firstPost = LocalDateTime.MAX;
        lastPost = LocalDateTime.MIN;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            rows ++;
            scoreSum += Integer.parseInt(attributes.getValue("Score"));
            LocalDateTime creationDate = LocalDateTime.parse(attributes.getValue("CreationDate"));
            if (creationDate.isBefore(firstPost)) {
                firstPost = creationDate;
            }
            if (creationDate.isAfter(lastPost)) {
                lastPost= creationDate;
            }
        }
    }

    public AnalyzeDetailsDTO getResults() {
        return new AnalyzeDetailsDTOBuilder()
                .withAvgScore(getAvgScore())
                .withFirstPost(firstPost)
                .withLastPost(lastPost)
                .withTotalPosts(rows)
                .build();
    }

    private int getAvgScore() {
        if (rows == 0) {
            return 0;
        }
        return (int) (scoreSum/rows);
    }
}
