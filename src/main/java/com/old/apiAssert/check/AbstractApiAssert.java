package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
/**
 * 异常的通用检查类
 * @author min
 */
public abstract class AbstractApiAssert<T, S extends ApiAssert<T, S, String>> implements ApiAssert<T, S, String> {

    @Override
    public S isNull(T t, String msg) {
        judge(t == null, msg);
        return self();
    }


    @Override
    public S nonNull(T t, String msg) {
        judge(t != null, msg);
        return self();
    }

    @Override
    public S isEmpty(T t, String msg) {
        if (t == null) {
            judge(true, msg);
        } else if (t instanceof Collection) {
            judge(((Collection<?>) t).isEmpty(), msg);
        } else if (t instanceof String) {
            judge(((String) t).isEmpty(), msg);
        } else if (t instanceof Map) {
            judge(((Map<?, ?>)t).isEmpty(), msg);
        } else if (t.getClass().isArray()) {
            judge(Array.getLength(t) == 0, msg);
        }
        return self();
    }

    @Override
    public S isTrue(boolean condition, String msg) {
        judge(condition, msg);
        return self();
    }

    @Override
    public S isFalse(boolean condition, String msg) {
        judge(!condition, msg);
        return self();
    }

    @Override
    public S process(Runnable handler) {
        handler.run();
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
     * @param message
     * @throws RuntimeException
     */
    protected abstract void established(String message) throws RuntimeException;

    protected void invalid() {

    }

}
