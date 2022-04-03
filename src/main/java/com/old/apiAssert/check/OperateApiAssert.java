package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;
import lombok.Data;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 传入对象，可以对对象与对象内部属性进行判断，如果条件成立会立即抛出异常
 * @author min
 */
@Data
public class OperateApiAssert<T> implements ApiAssert<T> {

    private final T obj;

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


    /**
     * 目前感觉这个还是有些不好，暂时不开放
     * @param msg
     * @param obj
     * @return
     * @throws RuntimeException
     */
    private OperateApiAssert<T> isNull(String msg, Object obj) throws RuntimeException {
        return (OperateApiAssert<T>) objectApiAssert.isNull(obj, msg);
    }


    private OperateApiAssert<T> isEmpty(String msg, Object obj) throws RuntimeException {
        return (OperateApiAssert<T>) objectApiAssert.isEmpty(obj, msg);
    }

    private OperateApiAssert<T> isTrue(String msg, boolean condition) throws RuntimeException {
        return (OperateApiAssert<T>) objectApiAssert.isTrue(condition, msg);
    }

    private OperateApiAssert<T> isFalse(String msg, boolean condition) throws RuntimeException {
        return (OperateApiAssert<T>) objectApiAssert.isFalse(condition, msg);
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
