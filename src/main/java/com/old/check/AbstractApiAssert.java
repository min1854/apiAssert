package com.old.check;

import com.old.api.ApiAssert;

import java.util.Collection;

public abstract class AbstractApiAssert<T> implements ApiAssert<T> {


    @Override
    public ApiAssert isNull(T t, String msg) {
        judge(t == null, msg);
        return this;
    }


    @Override
    public ApiAssert isEmpty(T t, String msg) {
        if (t == null) {
            judge(true, msg);
        } else if (t instanceof Collection) {
            judge(((Collection<?>) t).isEmpty(), msg);
        } else if (t instanceof String) {
            judge(((String) t).isEmpty(), msg);
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

    protected abstract void judge(boolean condition, String msg);


}
