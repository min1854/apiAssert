package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.check.operation.AbstractOperationApiAssert;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 传入对象，可以对对象与对象内部属性进行判断，如果条件成立会立即抛出异常
 *
 * @author min
 */
public class OperateApiAssert<T> extends AbstractOperationApiAssert<T, OperateApiAssert<T>, String> {


    public OperateApiAssert(T obj, Function<String, RuntimeException> exceptionGenerator) {
        super(obj, exceptionGenerator);
    }

    @Override
    protected <T, S extends ApiAssert<T, S, String>> S of(T t, Function<String, RuntimeException> exceptionGenerator) {
        return null;
    }

    public static <T> OperateApiAssert<T> create(T obj, Function<String, RuntimeException> exceptionFunction) {
        return new OperateApiAssert<T>(obj, exceptionFunction);
    }


    public static <T> OperateApiAssert<T> newInstance(T obj, Supplier<RuntimeException> exceptionSupplier) {
        return create(obj, msg -> {
            throw exceptionSupplier.get();
        });
    }


    @Override
    public OperateApiAssert<T> self() {
        return this;
    }

    @Override
    protected void established(String message) throws RuntimeException {

    }
}
