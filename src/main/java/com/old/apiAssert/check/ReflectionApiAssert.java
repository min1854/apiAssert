package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.exception.ApiAssertException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 指定异常的类型，反射创建异常，如果条件成立立即抛出
 *
 * @author min
 */
public class ReflectionApiAssert<E extends RuntimeException> extends ObjectStringApiAssert<ReflectionApiAssert<E>> {

    private Class<E> exception;


    public ReflectionApiAssert(Class<E> exception) {
        this.exception = exception;
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

    @Override
    public ReflectionApiAssert<E> self() {
        return this;
    }

    @Override
    protected void established(String msg) throws RuntimeException {
        throw createException(msg);
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

    public static <E extends RuntimeException> ApiAssert<Object, ReflectionApiAssert<E>, String> create(Class<E> exception) {
        return new ReflectionApiAssert<E>(exception);
    }

    private static class CreateFailException extends ApiAssertException {
        public CreateFailException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
