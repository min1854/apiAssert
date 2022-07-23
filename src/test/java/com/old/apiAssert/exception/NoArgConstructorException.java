package com.old.apiAssert.exception;

import lombok.NoArgsConstructor;

/**
 * 目前仅仅是一个作为测试异常
 * @author min
 */
public class NoArgConstructorException extends ApiAssertException{

    public NoArgConstructorException(String msg) {
        super(msg);
    }
}
