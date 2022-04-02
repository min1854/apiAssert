package com.old.api;


public interface ApiAssert<T> {

    ApiAssert<T> isNull(T obj, String msg);

    ApiAssert<T> isEmpty(T obj, String msg);

    ApiAssert<T> isTrue(boolean condition, String msg);

    ApiAssert<T> isFalse(boolean condition, String msg);
}
