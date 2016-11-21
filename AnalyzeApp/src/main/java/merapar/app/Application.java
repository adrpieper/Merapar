package merapar.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.parsers.SAXParserFactory;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);

    }

    @Bean
    public SAXParserFactory saxParserFactory() throws Exception{
        return SAXParserFactory.newInstance();
    }
}
