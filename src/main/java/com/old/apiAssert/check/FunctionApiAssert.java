package com.old.apiAssert.check;

import lombok.Data;

import java.util.Collection;
import java.util.function.Function;

/**
 * 这个可以说是我比较满意，或者说最满意的版本，
 * 第5个版本
 */
@Data
public class FunctionApiAssert<T extends RuntimeException> {

    private Function<String, T> exception;

    public static <T extends Throwable> FunctionApiAssert start(Function<String, T> f) {
        return new FunctionApiAssert(f);
    }

    public FunctionApiAssert(Function<String, T> exception) {
        this.exception = exception;
    }

    public FunctionApiAssert isNull(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        return this;
    }


    public FunctionApiAssert isEmpty(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        if (obj instanceof Collection) {
            throwBaseException(((Collection<?>) obj).isEmpty(), msg);
        } else if (obj instanceof String) {
            throwBaseException(((String) obj).isEmpty(), msg);
        }
        return this;
    }

    public FunctionApiAssert isTrue(boolean condition, String msg) {
        throwBaseException(condition, msg);
        return this;
    }

    public FunctionApiAssert isFalse(boolean condition, String msg) {
        throwBaseException(!condition, msg);
        return this;
    }


    private void throwBaseException(boolean condition, String msg) {
        if (condition) {
            throw exception.apply(msg);
        }
    }

}
