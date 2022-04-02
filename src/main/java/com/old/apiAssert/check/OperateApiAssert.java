package com.old.apiAssert.check;

import lombok.Data;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 第6个版本，另一种思路
 */
@Data
public class OperateApiAssert<T> {

    private T obj;


    public static <T> OperateApiAssert start(T obj) {
        return new OperateApiAssert(obj);
    }

    public OperateApiAssert(T obj) {
        this.obj = obj;
    }

    public OperateApiAssert isNull(String msg) {
        return isNull(obj, msg);
    }

    public OperateApiAssert isNull(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        return this;
    }

    public <R> OperateApiAssert isEmpty(Function<T, R> function, String msg) {
        isEmpty(function.apply(obj), msg);
        return this;
    }

    public OperateApiAssert isEmpty(String msg) {
        isEmpty(obj, msg);
        return this;
    }

    /**
     * @param function
     * @param msg
     * @return
     */
    public OperateApiAssert process(Function<T, Boolean> function, String msg) {
        throwBaseException(function.apply(obj), msg);
        return this;
    }

    public OperateApiAssert process(Consumer<T> consumer) {
        consumer.accept(this.obj);
        return this;
    }


    public OperateApiAssert isEmpty(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        if (obj instanceof Collection) {
            throwBaseException(((Collection<?>) obj).isEmpty(), msg);
        } else if (obj instanceof String) {
            throwBaseException(((String) obj).isEmpty(), msg);
        }
        return this;
    }

    public OperateApiAssert isTrue(boolean condition, String msg) {
        throwBaseException(condition, msg);
        return this;
    }

    public OperateApiAssert isFalse(boolean condition, String msg) {
        throwBaseException(!condition, msg);
        return this;
    }


    private void throwBaseException(boolean condition, String msg) {
        if (condition) {
            //throw exception.apply(msg);
        }
    }

}
