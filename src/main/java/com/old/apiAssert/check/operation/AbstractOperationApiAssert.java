package com.old.apiAssert.check.operation;

import com.old.apiAssert.api.ApiAssert;
import com.old.apiAssert.check.AbstractApiAssert;
import com.old.apiAssert.check.OperateApiAssert;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 内部类的泛型要么全部都是泛型，要么全部都是指定类型
 * @param <T>
 * @param <S>
 * @param <M>
 */
public abstract class AbstractOperationApiAssert<T, S extends AbstractOperationApiAssert<T, S, M>, M> extends AbstractApiAssert<T, S, M> {


    private T obj;

    private Function<M, RuntimeException> exceptionGenerator;

    private Function<M, String> messageConverter;


    private ObjectExceptionGenerator<M> apiAssert = new ObjectExceptionGenerator<M>() {
        @Override
        protected void established(M message) throws RuntimeException {
            throw exceptionGenerator.apply(message);
        }

    };


    public AbstractOperationApiAssert(T obj, Function<M, RuntimeException> exceptionGenerator) {
        this.obj = obj;
        this.exceptionGenerator = exceptionGenerator;
    }

    public T getObj() {
        return obj;
    }

    public String convertMessage(M message) {
        return messageConverter.apply(message);
    }


    public S nonNull(M message) {
        apiAssert.nonNull(this.obj, message);
        return self();
    }

    public <R> S nonNull(Function<T, R> function, M message) {
        apiAssert.nonNull(function.apply(this.obj), message);
        return self();
    }

    public <R> S nonNull(Function<T, R> function, Function<T, M> message) {
        apiAssert.nonNull(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    public S isNull(M message) {
        apiAssert.isNull(this.obj, message);
        return self();
    }

    public <R> S isNull(Function<T, R> function, M message) {
        apiAssert.isNull(function.apply(this.obj), message);
        return self();
    }

    public <R> S isNull(Function<T, R> function, Function<T, M> message) {
        apiAssert.isNull(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    public <R> S isEmpty(Function<T, R> function, M message) {
        apiAssert.isEmpty(function.apply(this.obj), message);
        return self();
    }

    public S isEmpty(M message) {
        apiAssert.isEmpty(this.obj, message);
        return self();
    }

    public <R> S isEmpty(Function<T, R> function, Function<T, M> message) {
        apiAssert.isEmpty(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    public S isTrue(Function<T, Boolean> function, M message) {
        apiAssert.isTrue(function.apply(this.obj), message);
        return self();
    }

    public S isTrue(Function<T, Boolean> function, Function<T, M> message) {
        apiAssert.isTrue(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    public S isFalse(Function<T, Boolean> function, M message) {
        apiAssert.isFalse(function.apply(this.obj), message);
        return self();
    }

    public S isFalse(Function<T, Boolean> function, Function<T, M> message) {
        apiAssert.isFalse(function.apply(this.obj), message.apply(this.obj));
        return self();
    }


    public <T> S then(T t) {
        return null;
    }

    public <T> S then(Supplier<T> supplier) {
        return of(supplier.get(), exceptionGenerator);
    }

    public <T> S then(Supplier<T> supplier, Function<M, RuntimeException> exceptionGenerator) {
        return of(supplier.get(), exceptionGenerator);
    }

    public <R> S then(Function<T, R> function) {
        return of(function.apply(this.obj), exceptionGenerator);
    }

    public <R> S then(Function<T, R> function, Function<M, RuntimeException> exceptionGenerator) {
        return of(function.apply(this.obj), exceptionGenerator);
    }

    public S process(Consumer<T> consumer) {
        consumer.accept(this.obj);
        return self();
    }

    public S process(BiConsumer<T, ApiAssert<T, S, M>> consumer) {
        consumer.accept(this.obj, this);
        return self();
    }



    protected <T> S of(T t, Function<M, RuntimeException> exceptionGenerator) {
        return null;
    }
}
