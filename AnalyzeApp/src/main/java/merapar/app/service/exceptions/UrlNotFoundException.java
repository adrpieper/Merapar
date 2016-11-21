package merapar.app.service.exceptions;

import java.net.URL;

public class UrlNotFoundException extends RuntimeException {

    public UrlNotFoundException(URL fileUrl, Throwable e) {
        super("Can't find file " + fileUrl.getPath(), e);
    }
}
