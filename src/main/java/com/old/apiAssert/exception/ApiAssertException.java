package com.old.apiAssert.exception;

import lombok.NoArgsConstructor;

/**
 * api-assert 框架的顶级异常父类，
 * @author min
 */
@NoArgsConstructor
public class ApiAssertException extends RuntimeException {

    public ApiAssertException(String message) {
        super(message);
    }

    public ApiAssertException(String message, Throwable cause) {
        super(message, cause);
    }
}
