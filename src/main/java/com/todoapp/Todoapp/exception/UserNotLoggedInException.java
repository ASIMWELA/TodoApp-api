package com.todoapp.Todoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserNotLoggedInException extends RuntimeException
{
    private String message;

    public UserNotLoggedInException(String message) {
        super(message);
        this.message = message;
    }

    public UserNotLoggedInException(String message, Throwable cause) {
        super(message, cause);
    }
}
