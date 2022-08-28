package com.old.apiAssert.exception;


import com.old.apiAssert.enums.AssertEnum;

public class EnumMessageException extends ApiAssertException {

    public AssertEnum assertEnum;

    public EnumMessageException(AssertEnum assertEnum) {
        this.assertEnum = assertEnum;
    }
}
