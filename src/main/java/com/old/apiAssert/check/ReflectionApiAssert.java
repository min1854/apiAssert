package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.exception.ApiAssertException;
import com.old.apiAssert.exception.BaseException;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 不够通用，问题在于 exception 的 msg 只能在构造方法中赋值，无法使用 set 方法
 * <p>
 * 这个足够通用了，但是又感觉有些鸡肋，或是有些过分了
 * <p>
 * 第2个版本
 **/
@Getter
public class ReflectionApiAssert<E extends RuntimeException> implements ApiAssert<Object> {

    private Class<E> exception;

    private ApiAssert apiAssert;

    // private static final Map<Class<RuntimeException>, Constructor<RuntimeException>> MAP = new ConcurrentHashMap<>();


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

    private static class CreateFailException extends ApiAssertException {
        public CreateFailException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
