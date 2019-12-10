package net.cookingbook.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Post was not found!")
public class PostNotFoundException extends RuntimeException {

    private int statusCode;

    public PostNotFoundException() {
        this.statusCode = 404;
    }

    public PostNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
