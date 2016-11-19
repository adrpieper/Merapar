package merapar.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public class AnalyzeRequestDTO {
    private final URL url;

    public AnalyzeRequestDTO(@JsonProperty("url") URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }
}
