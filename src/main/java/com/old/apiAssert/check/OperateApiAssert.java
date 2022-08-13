package com.old.apiAssert.check;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 传入对象，可以对对象与对象内部属性进行判断，如果条件成立会立即抛出异常
 *
 * @author min
 */
public class OperateApiAssert<T> extends ObjectApiAssert<OperateApiAssert<T>> {

    private final T obj;

    private Function<String, RuntimeException> exceptionFunction;


    public static <T> OperateApiAssert<T> create(T obj, Function<String, RuntimeException> exceptionFunction) {
        return new OperateApiAssert<T>(obj, exceptionFunction);
    }


    public static <T> OperateApiAssert<T> newInstance(T obj, Supplier<RuntimeException> exceptionSupplier) {
        return create(obj, msg -> {
            throw exceptionSupplier.get();
        });
    }

    public OperateApiAssert(T obj, Function<String, RuntimeException> exceptionFunction) {
        this.obj = obj;
        this.exceptionFunction = exceptionFunction;
    }

    public T getObj() {
        return obj;
    }

    public OperateApiAssert<T> nonNull(String msg) {
        return super.nonNull(this.obj, msg);
    }

    public <R> OperateApiAssert<T> nonNull(Function<T, R> function, String msg) {
        return super.nonNull(function.apply(this.obj), msg);
    }

    public <R> OperateApiAssert<T> nonNull(Function<T, R> function, Function<T, String> msg) {
        return super.nonNull(function.apply(this.obj), msg.apply(this.obj));
    }

    public OperateApiAssert<T> isNull(String msg) {
        return super.isNull(this.obj, msg);
    }

    public <R> OperateApiAssert<T> isNull(Function<T, R> function, String msg) {
        return super.isNull(function.apply(this.obj), msg);
    }

    public <R> OperateApiAssert<T> isNull(Function<T, R> function, Function<T, String> msg) {
        return super.isNull(function.apply(this.obj), msg.apply(this.obj));
    }

    public <R> OperateApiAssert<T> isEmpty(Function<T, R> function, String msg) {
        return super.isEmpty(function.apply(this.obj), msg);
    }

    public OperateApiAssert<T> isEmpty(String msg) {
        return super.isEmpty(this.obj, msg);
    }

    public <R> OperateApiAssert<T> isEmpty(Function<T, R> function, Function<T, String> msg) {
        return super.isEmpty(function.apply(this.obj), msg.apply(this.obj));
    }

    public OperateApiAssert<T> isTrue(Function<T, Boolean> function, String msg) {
        Boolean condition = function.apply(this.obj);
        super.isNull(condition, "校验结果为空");
        return super.isTrue(condition, msg);
    }

    public OperateApiAssert<T> isTrue(Function<T, Boolean> function, Function<T, String> msg) {
        Boolean condition = function.apply(this.obj);
        super.isNull(condition, "校验结果为空");
        return super.isTrue(condition, msg.apply(this.obj));
    }

    public OperateApiAssert<T> isFalse(Function<T, Boolean> function, String msg) {
        Boolean condition = function.apply(this.obj);
        super.isNull(condition, "校验结果为空");
        return super.isFalse(condition, msg);
    }

    public OperateApiAssert<T> isFalse(Function<T, Boolean> function, Function<T, String> msg) {
        Boolean condition = function.apply(this.obj);
        super.isNull(condition, "校验结果为空");
        return super.isFalse(condition, msg.apply(this.obj));
    }


    /**
     * 名字有误，用这个{@link OperateApiAssert#then(Object)} 后续版本会删除
     *
     * @param t
     * @param <T>
     * @return
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

    public OperateApiAssert<T> process(Consumer<T> consumer) {
        consumer.accept(this.obj);
        return this.self();
    }

    @Override
    public OperateApiAssert<T> self() {
        return this;
    }

    @Override
    protected void established(String msg) throws RuntimeException {
        throw exceptionFunction.apply(msg);
    }
}
