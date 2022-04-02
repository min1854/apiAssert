package com.old.apiAssert.api;


import java.util.function.Supplier;

public interface ApiAssert<T> {

    ApiAssert<T> isNull(T obj, String msg) throws RuntimeException;

    ApiAssert<T> isEmpty(T obj, String msg) throws RuntimeException;

    ApiAssert<T> isTrue(boolean condition, String msg) throws RuntimeException;

    ApiAssert<T> isFalse(boolean condition, String msg) throws RuntimeException;

    default void throwThrowable(Supplier<Throwable> throwable) throws Throwable {
        throw throwable.get();
    }

    default void throwThrowable(Throwable throwable) throws Throwable {
        throw throwable;
    }

    default void throwRuntime(Supplier<RuntimeException> exception) throws RuntimeException {
        throw exception.get();
    }

    default void throwRuntime(RuntimeException exception) throws RuntimeException {
        throw exception;
    }
}
