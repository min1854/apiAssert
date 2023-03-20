package io.github.min1854.apiAssert.check.abstractAssert;

import io.github.min1854.apiAssert.api.StandardApiAssert;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 异常的通用检查类
 *
 * @author min
 */
public abstract class AbstractApiAssert<T, SELF extends AbstractApiAssert<T, SELF, M>, M> implements StandardApiAssert<T, SELF, M> {

    @Override
    public SELF isNull(T t, M message) {
        judge(t == null, message);
        return self();
    }


    @Override
    public SELF nonNull(T t, M message) {
        judge(t != null, message);
        return self();
    }

    @Override
    public SELF isEmpty(T t, M message) {
        if (t == null) {
            judge(true, message);
        } else if (t instanceof Collection) {
            judge(((Collection<?>) t).isEmpty(), message);
        } else if (t instanceof String) {
            judge(((String) t).isEmpty(), message);
        } else if (t instanceof Map) {
            judge(((Map<?, ?>) t).isEmpty(), message);
        } else if (t.getClass().isArray()) {
            judge(Array.getLength(t) == 0, message);
        }
        return self();
    }

    @Override
    public SELF isTrue(boolean condition, M message) {
        judge(condition, message);
        return self();
    }

    @Override
    public SELF isFalse(boolean condition, M message) {
        judge(!condition, message);
        return self();
    }

    /**
     * 判断条件是否成立
     *
     * @param condition
     * @param message
     */
    protected void judge(boolean condition, M message) {
        if (condition) {
            established(message);
        } else {
            invalid(message);
        }
    }

    /**
     * 条件成立处理方法 由子类实现
     *
     * @param message
     * @throws RuntimeException
     */
    protected abstract void established(M message) throws RuntimeException;

    protected void invalid(M message) {

    }

}
