package com.old.apiAssert.api;


import java.util.function.Function;

public interface ChainApiAssert<T> extends ApiAssert<T> {

    <R> ChainApiAssert<T> isEmpty(Function<T, R> function, String msg);

    //ChainApiAssert<T> isEmpty(Function<T, Boolean> function, String msg);


}
