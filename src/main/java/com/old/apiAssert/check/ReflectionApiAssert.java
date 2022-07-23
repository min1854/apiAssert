package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.exception.ApiAssertException;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 指定异常的类型，反射创建异常，如果条件成立立即抛出
 * @author min
 */
public class ReflectionApiAssert<E extends RuntimeException> implements ApiAssert<Object> {

    private Class<E> exception;

    private ApiAssert apiAssert;

    private ReflectionApiAssert(Class<E> exception) {
        this.exception = exception;
        apiAssert = new ObjectApiAssert() {
            @Override
            protected void established(String msg) throws RuntimeException {
                throw createException(msg);
            }

            @Override
            protected <S extends ApiAssert<Object>> S self() {
                return (S) ReflectionApiAssert.this;
            }
        };
    }


    private RuntimeException createException(String msg) {
        try {
            Constructor<E> constructor = findConstructor(ReflectionApiAssert.this.getException());
            if (constructor.getParameters().length >= 1) {
                return constructor.newInstance(msg);
            } else {
                return constructor.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new CreateFailException(msg, e);
        }
    }

    public Class<E> getException() {
        return exception;
    }

    private Constructor<E> findConstructor(Class<E> eClass) {
        Constructor<E> constructor = null;
        try {
            constructor = eClass.getConstructor(String.class);
            if (constructor != null) {
                return constructor;
            }
        } catch (NoSuchMethodException e) {
        }

        try {
            return eClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new CreateFailException("无法创建指定异常", e);
        }
    }

    public static <E extends RuntimeException> ApiAssert<Object> create(Class<E> exception) {
        return new ReflectionApiAssert<E>(exception);
    }

    @Override
    public ApiAssert<Object> isNull(Object obj, String msg) {
        return apiAssert.isNull(obj, msg);
    }


    @Override
    public ApiAssert<Object> isEmpty(Object obj, String msg) {
        return apiAssert.isEmpty(obj, msg);
    }

    @Override
    public ApiAssert<Object> isTrue(boolean condition, String msg) {
        return apiAssert.isTrue(condition, msg);
    }

    @Override
    public ApiAssert<Object> isFalse(boolean condition, String msg) {
        return apiAssert.isFalse(condition, msg);
    }

    @Override
    public ApiAssert<Object> process(Runnable handler) {
        return apiAssert.process(handler);
    }

    private static class CreateFailException extends ApiAssertException {
        public CreateFailException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
