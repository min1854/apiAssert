package com.old.apiAssert.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiAssertException extends RuntimeException {

    public ApiAssertException(String message) {
        super(message);
    }

    public ApiAssertException(String message, Throwable cause) {
        super(message, cause);
    }
}
