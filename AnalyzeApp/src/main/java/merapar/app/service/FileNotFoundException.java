package merapar.app.service;

import java.net.URL;

public class FileNotFoundException extends RuntimeException {
    private final URL fileUrl;

    public FileNotFoundException(Throwable e,URL fileUrl) {
        super(e);
        this.fileUrl = fileUrl;
    }
}
