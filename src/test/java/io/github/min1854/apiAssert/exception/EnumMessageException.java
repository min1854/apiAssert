package io.github.min1854.apiAssert.exception;


import io.github.min1854.apiAssert.enums.AssertEnum;

public class EnumMessageException extends ApiAssertException {

    public AssertEnum assertEnum;

    public EnumMessageException(AssertEnum assertEnum) {
        this.assertEnum = assertEnum;
    }
}
