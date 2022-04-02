package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;

import java.util.Collection;

public abstract class AbstractApiAssert<T> implements ApiAssert<T> {


    @Override
    public ApiAssert<T> isNull(T t, String msg) {
        judge(t == null, msg);
        return this;
    }


    @Override
    public ApiAssert<T> isEmpty(T t, String msg) {
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
    public ApiAssert<T> isTrue(boolean condition, String msg) {
        judge(condition, msg);
        return this;
    }

    @Override
    public ApiAssert<T> isFalse(boolean condition, String msg) {
        judge(!condition, msg);
        return this;
    }

    protected void judge(boolean condition, String msg) {
        if(condition) {
            established(msg);
        } else {
            invalid();
        }
    }

    /**
     * 条件成立处理方法 由子类实现
     * @param msg
     * @throws RuntimeException
     */
    protected abstract void established(String msg) throws RuntimeException;

    protected void invalid() {

    }


}
