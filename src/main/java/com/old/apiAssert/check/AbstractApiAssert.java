package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * 异常的通用检查类
 * @author min
 */
public abstract class AbstractApiAssert<T> implements ApiAssert<T> {

    @Override
    public ApiAssert<T> isNull(T t, String msg) {
        judge(t == null, msg);
        return self();
    }


    @Override
    public ApiAssert<T> nonNull(T t, String msg) {
        judge(t != null, msg);
        return self();
    }

    @Override
    public ApiAssert<T> isEmpty(T t, String msg) {
        if (t == null) {
            judge(true, msg);
        } else if (t instanceof Collection) {
            judge(((Collection<?>) t).isEmpty(), msg);
        } else if (t instanceof String) {
            judge(((String) t).isEmpty(), msg);
        } else if(t.getClass().isArray()) {
            judge(Array.getLength(t) == 0, msg);
        }
        return self();
    }

    @Override
    public ApiAssert<T> isTrue(boolean condition, String msg) {
        judge(condition, msg);
        return self();
    }

    @Override
    public ApiAssert<T> isFalse(boolean condition, String msg) {
        judge(!condition, msg);
        return self();
    }

    /**
     * 判断条件是否成立
     * @param condition
     * @param msg
     */
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

    /**
     * 返回自身
     * @param <S>
     * @return
     */
    protected abstract <S extends ApiAssert<T>> S self();

}
