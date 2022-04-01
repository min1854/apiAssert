package com.old;


public interface ApiAssert<T> {

    ApiAssert<T> isNull(Object obj, String msg);

    ApiAssert<T> isEmpty(Object obj, String msg);

    ApiAssert<T> isTrue(boolean condition, String msg);

    ApiAssert<T> isFalse(boolean condition, String msg);
}
