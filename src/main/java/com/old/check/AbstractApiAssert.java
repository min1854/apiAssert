package com.old.check;

import com.old.ApiAssert;

import java.util.Collection;

public abstract class AbstractApiAssert implements ApiAssert {


    @Override
    public ApiAssert isNull(Object obj, String msg) {
        judge(obj == null, msg);
        return this;
    }


    @Override
    public ApiAssert isEmpty(Object obj, String msg) {
        judge(obj == null, msg);
        if (obj instanceof Collection) {
            judge(((Collection<?>) obj).isEmpty(), msg);
        } else if (obj instanceof String) {
            judge(((String) obj).isEmpty(), msg);
        }
        return this;
    }

    @Override
    public ApiAssert isTrue(boolean condition, String msg) {
        judge(condition, msg);
        return this;
    }

    @Override
    public ApiAssert isFalse(boolean condition, String msg) {
        judge(!condition, msg);
        return this;
    }

    protected void judge(boolean condition, String msg) {

    }


}
