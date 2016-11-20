package merapar.app.controller.dto.jackson;

import java.time.format.DateTimeFormatter;

public class ZonedDateTimeFormatter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx");

    public static DateTimeFormatter getInstance() {
        return formatter;
    }
}
