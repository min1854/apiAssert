package com.old.check;

import com.old.BaseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * 不够通用，问题在于 exception 的 msg 只能在构造方法中赋值，无法使用 set 方法
 *
 * 这个足够通用了，但是又感觉有些鸡肋，或是有些过分了
 *
 * 第2个版本
 **/
public class RelectionApiAssert {

    private Class<BaseException> exception;

    private RelectionApiAssert(Class<BaseException> exception) {
        this.exception = exception;
    }

    public static RelectionApiAssert start(Class<BaseException> exception) {
        return new RelectionApiAssert(exception);
    }


    public RelectionApiAssert isNull(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        return this;
    }


    public RelectionApiAssert isEmpty(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        if (obj instanceof Collection) {
            throwBaseException(((Collection<?>) obj).isEmpty(), msg);
        } else if (obj instanceof String) {
            throwBaseException(((String) obj).isEmpty(), msg);
        }
        return this;
    }

    public RelectionApiAssert isTrue(boolean condition, String msg) {
        throwBaseException(condition, msg);
        return this;
    }

    public RelectionApiAssert isFalse(boolean condition, String msg) {
        throwBaseException(!condition, msg);
        return this;
    }


    private void throwBaseException(boolean condition, String msg) {
        if (condition) {
            try {
                Constructor<BaseException> constructor = exception.getConstructor(String.class);
                constructor.setAccessible(true);
                throw constructor.newInstance(msg);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new CreateFailException(msg, e);
            }
        }
    }

    private static class CreateFailException extends RuntimeException {
        public CreateFailException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
