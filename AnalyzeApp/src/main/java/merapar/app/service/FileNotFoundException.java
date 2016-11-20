package merapar.app.service;

import java.net.URL;

public class FileNotFoundException extends RuntimeException {
    private final URL fileUrl;

    public FileNotFoundException(URL fileUrl) {
        this.fileUrl = fileUrl;
    }
}
