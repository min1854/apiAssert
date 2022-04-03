package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;
import lombok.Data;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 第6个版本，另一种思路
 */
@Data
public class OperateApiAssert<T> implements ApiAssert<T> {

    private T obj;

    private Function<String, RuntimeException> exceptionFunction;

    private InternalAssert internalAssert;

    private ObjectApiAssert objectApiAssert;


    public static <T> OperateApiAssert<T> create(T obj, Function<String, RuntimeException> exceptionFunction) {
        return new OperateApiAssert<T>(obj, exceptionFunction);
    }


    public static <T> OperateApiAssert<T> create(T obj, Supplier<RuntimeException> exceptionSupplier) {
        return new OperateApiAssert<T>(obj, msg -> exceptionSupplier.get());
    }

    public static <T> OperateApiAssert<T> newInstance(T obj, Supplier<RuntimeException> exceptionSupplier) {
        return create(obj, exceptionSupplier);
    }

    public OperateApiAssert(T obj, Function<String, RuntimeException> exceptionFunction) {
        this.obj = obj;
        this.exceptionFunction = exceptionFunction;
        internalAssert = new InternalAssert();
        objectApiAssert = new ObjectApiAssert() {
            @Override
            protected void established(String msg) throws RuntimeException {
                internalAssert.throwRuntime(exceptionFunction.apply(msg));
            }

            @Override
            protected <S extends ApiAssert<Object>> S self() {
                return (S) OperateApiAssert.this;
            }
        };
    }

    private class InternalAssert extends AbstractApiAssert<T> {

        @Override
        protected void established(String msg) throws RuntimeException {
            throw exceptionFunction.apply(msg);
        }

        @Override
        protected ApiAssert<T> self() {
            return (ApiAssert<T>) OperateApiAssert.this;
        }
    }

    public OperateApiAssert<T> isNull(String msg) {
        return (OperateApiAssert<T>) internalAssert.isNull(this.obj, msg);
    }

    public <R> OperateApiAssert<T> isNull(Function<T, R> function, String msg) {
        return (OperateApiAssert<T>) objectApiAssert.isNull(function.apply(this.obj), msg);
    }

    public <R> OperateApiAssert<T> isEmpty(Function<T, R> function, String msg) {
        return (OperateApiAssert<T>) objectApiAssert.isEmpty(function.apply(this.obj), msg);
    }

    public OperateApiAssert<T> isEmpty(String msg) {
        return (OperateApiAssert<T>) internalAssert.isEmpty(this.obj, msg);
    }

    public OperateApiAssert<T> isTrue(Function<T, Boolean> function, String msg) {
        Boolean condition = function.apply(this.obj);
        objectApiAssert.isNull(condition, "校验结果为空");
        return (OperateApiAssert<T>) objectApiAssert.isTrue(condition, msg);
    }

    public OperateApiAssert<T> isFalse(Function<T, Boolean> function, String msg) {
        Boolean condition = function.apply(this.obj);
        objectApiAssert.isNull(condition, "校验结果为空");
        return (OperateApiAssert<T>) objectApiAssert.isFalse(function.apply(this.obj), msg);
    }





    @Override
    public OperateApiAssert<T> isNull(T obj, String msg) throws RuntimeException {
        return (OperateApiAssert<T>) internalAssert.isNull(obj, msg);
    }

    @Override
    public OperateApiAssert<T> isEmpty(T obj, String msg) throws RuntimeException {
        return (OperateApiAssert<T>) internalAssert.isEmpty(obj, msg);
    }

    @Override
    public OperateApiAssert<T> isTrue(boolean condition, String msg) throws RuntimeException {
        return (OperateApiAssert<T>) internalAssert.isTrue(condition, msg);
    }

    @Override
    public OperateApiAssert<T> isFalse(boolean condition, String msg) throws RuntimeException {
        return (OperateApiAssert<T>) internalAssert.isFalse(condition, msg);
    }

}
