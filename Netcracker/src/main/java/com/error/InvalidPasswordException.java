package com.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Admin on 22.02.2019.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "invalid password")
@SuppressWarnings("serial")
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
