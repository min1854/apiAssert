package com.old.apiAssert.check;

import com.old.apiAssert.api.ApiAssert;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 传入对象，可以对对象与对象内部属性进行判断，如果条件成立会立即抛出异常
 * @author min
 */
public class OperateApiAssert<T> implements ApiAssert<Object> {

    private final T obj;

    private Function<String, RuntimeException> exceptionFunction;

    private ObjectApiAssert objectApiAssert;


    public static <T> OperateApiAssert<T> create(T obj, Function<String, RuntimeException> exceptionFunction) {
        return new OperateApiAssert<T>(obj, exceptionFunction);
    }


    /**
     * 与 {@link OperateApiAssert#newInstance(Object, Supplier)} 重复，后续删除
     * @param obj
     * @param exceptionSupplier
     * @return
     * @param <T>
     */
    @Deprecated
    public static <T> OperateApiAssert<T> create(T obj, Supplier<RuntimeException> exceptionSupplier) {
        return new OperateApiAssert<T>(obj, msg -> exceptionSupplier.get());
    }

    public static <T> OperateApiAssert<T> newInstance(T obj, Supplier<RuntimeException> exceptionSupplier) {
        return create(obj, exceptionSupplier);
    }

    public OperateApiAssert(T obj, Function<String, RuntimeException> exceptionFunction) {
        this.obj = obj;
        this.exceptionFunction = exceptionFunction;
        objectApiAssert = new ObjectApiAssert() {
            @Override
            protected void established(String msg) throws RuntimeException {
                this.throwRuntime(exceptionFunction.apply(msg));
            }

            @Override
            protected <S extends ApiAssert<Object>> S self() {
                return (S) OperateApiAssert.this;
            }
        };
    }

    public T getObj() {
        return obj;
    }

    public OperateApiAssert<T> nonNull(String msg) {
        return (OperateApiAssert<T>) objectApiAssert.nonNull(this.obj, msg);
    }

    public <R> OperateApiAssert<T> nonNull(Function<T, R> function, String msg) {
        return (OperateApiAssert<T>) objectApiAssert.nonNull(function.apply(this.obj), msg);
    }

    public <R> OperateApiAssert<T> nonNull(Function<T, R> function, Function<T, String> msg) {
        return (OperateApiAssert<T>) objectApiAssert.nonNull(function.apply(this.obj), msg.apply(this.obj));
    }

    public OperateApiAssert<T> isNull(String msg) {
        return (OperateApiAssert<T>) objectApiAssert.isNull(this.obj, msg);
    }

    public <R> OperateApiAssert<T> isNull(Function<T, R> function, String msg) {
        return (OperateApiAssert<T>) objectApiAssert.isNull(function.apply(this.obj), msg);
    }

    public <R> OperateApiAssert<T> isNull(Function<T, R> function, Function<T, String> msg) {
        return (OperateApiAssert<T>) objectApiAssert.isNull(function.apply(this.obj), msg.apply(this.obj));
    }

    public <R> OperateApiAssert<T> isEmpty(Function<T, R> function, String msg) {
        return (OperateApiAssert<T>) objectApiAssert.isEmpty(function.apply(this.obj), msg);
    }

    public OperateApiAssert<T> isEmpty(String msg) {
        return (OperateApiAssert<T>) objectApiAssert.isEmpty(this.obj, msg);
    }

    public <R> OperateApiAssert<T> isEmpty(Function<T, R> function, Function<T, String> msg) {
        return (OperateApiAssert<T>) objectApiAssert.isEmpty(function.apply(this.obj), msg.apply(this.obj));
    }

    public OperateApiAssert<T> isTrue(Function<T, Boolean> function, String msg) {
        Boolean condition = function.apply(this.obj);
        objectApiAssert.isNull(condition, "校验结果为空");
        return (OperateApiAssert<T>) objectApiAssert.isTrue(condition, msg);
    }

    public OperateApiAssert<T> isTrue(Function<T, Boolean> function, Function<T, String> msg) {
        Boolean condition = function.apply(this.obj);
        objectApiAssert.isNull(condition, "校验结果为空");
        return (OperateApiAssert<T>) objectApiAssert.isTrue(condition, msg.apply(this.obj));
    }

    public OperateApiAssert<T> isFalse(Function<T, Boolean> function, String msg) {
        Boolean condition = function.apply(this.obj);
        objectApiAssert.isNull(condition, "校验结果为空");
        return (OperateApiAssert<T>) objectApiAssert.isFalse(condition, msg);
    }

    public OperateApiAssert<T> isFalse(Function<T, Boolean> function, Function<T, String> msg) {
        Boolean condition = function.apply(this.obj);
        objectApiAssert.isNull(condition, "校验结果为空");
        return (OperateApiAssert<T>) objectApiAssert.isFalse(condition, msg.apply(this.obj));
    }


    /**
     * 名字有误，用这个{@link OperateApiAssert#then(Object)} 后续版本会删除
     * @param t
     * @return
     * @param <T>
     */
    @Deprecated
    public <T> OperateApiAssert<T> when(T t) {
        return new OperateApiAssert<T>(t, this.exceptionFunction);
    }


    public <T> OperateApiAssert<T> then(T t) {
        return new OperateApiAssert<T>(t, this.exceptionFunction);
    }

    public <T> OperateApiAssert<T> then(Supplier<T> t) {
        return new OperateApiAssert<T>(t.get(), this.exceptionFunction);
    }

    public <R> OperateApiAssert<R> then(Function<T, R> function) {
        return new OperateApiAssert<R>(function.apply(this.obj), this.exceptionFunction);
    }

    @Override
    public OperateApiAssert<Object> isNull(Object obj, String msg) throws RuntimeException {
        return (OperateApiAssert<Object>) objectApiAssert.isNull(obj, msg);
    }

    @Override
    public OperateApiAssert<Object> nonNull(Object obj, String msg) {
        return (OperateApiAssert<Object>) objectApiAssert.nonNull(obj, msg);
    }

    @Override
    public OperateApiAssert<Object> isEmpty(Object obj, String msg) throws RuntimeException {
        return (OperateApiAssert<Object>) objectApiAssert.isEmpty(obj, msg);
    }

    @Override
    public OperateApiAssert<T> isTrue(boolean condition, String msg) throws RuntimeException {
        return (OperateApiAssert<T>) objectApiAssert.isTrue(condition, msg);
    }

    @Override
    public OperateApiAssert<T> isFalse(boolean condition, String msg) throws RuntimeException {
        return (OperateApiAssert<T>) objectApiAssert.isFalse(condition, msg);
    }

    @Override
    public OperateApiAssert<T> process(Runnable handler) {
        return (OperateApiAssert<T>) objectApiAssert.process(handler);
    }

    public OperateApiAssert<T> process(Consumer<T> consumer) {
        consumer.accept(this.obj);
        return (OperateApiAssert<T>) objectApiAssert.self();
    }

}
