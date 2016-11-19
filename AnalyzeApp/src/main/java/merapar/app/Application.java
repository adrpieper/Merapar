package merapar.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);

    }

    @Bean
    public SAXParser saxParser() throws Exception{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        return factory.newSAXParser();
    }
}
