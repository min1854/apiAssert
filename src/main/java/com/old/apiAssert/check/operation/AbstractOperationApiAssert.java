package com.old.apiAssert.check.operation;

import com.old.apiAssert.api.OptionalApiAssert;

import java.util.function.*;

/**
 * 内部类的泛型要么全部都是泛型，要么全部都是指定类型
 *
 * @param <ELEMENT>
 * @param <S>
 * @param <MESSAGE>
 */
public abstract class AbstractOperationApiAssert<ELEMENT, SELF extends AbstractOperationApiAssert<ELEMENT, SELF, MESSAGE>, MESSAGE>
        implements OptionalApiAssert<ELEMENT, SELF, MESSAGE> {


    protected final ELEMENT obj;

    protected Function<MESSAGE, RuntimeException> exceptionGenerator;

    private Function<MESSAGE, String> messageConverter;


    private ObjectExceptionGenerator<MESSAGE> apiAssert = new ObjectExceptionGenerator<MESSAGE>() {
        @Override
        protected void established(MESSAGE message) throws RuntimeException {
            throw exceptionGenerator.apply(message);
        }

    };


    public AbstractOperationApiAssert(ELEMENT obj, Function<MESSAGE, RuntimeException> exceptionGenerator) {
        this.obj = obj;
        this.exceptionGenerator = exceptionGenerator;
        /*new AbstractOperationApiAssert<ELEMENT, APIASSERTOBJECT, S, MESSAGE>(obj, exceptionGenerator){

            @Override
            protected <ELEMENT,  S extends AbstractOperationApiAssert<ELEMENT, APIASSERTOBJECT, S, MESSAGE>> S of(ELEMENT ELEMENT) {
                return AbstractOperationApiAssert.this.of(ELEMENT);
            }

            @Override
            protected void established(MESSAGE message) throws RuntimeException {
                exceptionGenerator.apply(message);
            }

            @Override
            public S self() {
                return AbstractOperationApiAssert.this.self();
            }
        };*/
    }

    public SELF self() {
        return null;
    }

    public ELEMENT getObj() {
        return obj;
    }

    public Function<MESSAGE, RuntimeException> getExceptionGenerator() {
        return exceptionGenerator;
    }

    public SELF setExceptionGenerator(Function<MESSAGE, RuntimeException> exceptionGenerator) {
        this.exceptionGenerator = exceptionGenerator;
        return self();
    }

    public String convertMessage(MESSAGE message) {
        return messageConverter.apply(message);
    }


    @Override
    public SELF nonNull(MESSAGE message) {
        apiAssert.nonNull(this.obj, message);
        return self();
    }

    @Override
    public <R> SELF nonNull(Function<ELEMENT, R> function, MESSAGE message) {
        apiAssert.nonNull(function.apply(this.obj), message);
        return self();
    }

    @Override
    public <R> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        apiAssert.nonNull(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    @Override
    public SELF isNull(MESSAGE message) {
        apiAssert.isNull(this.obj, message);
        return self();
    }

    @Override
    public <R> SELF isNull(Function<ELEMENT, R> function, MESSAGE message) {
        apiAssert.isNull(function.apply(this.obj), message);
        return self();
    }

    @Override
    public <R> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        apiAssert.isNull(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    @Override
    public <R> SELF isEmpty(Function<ELEMENT, R> function, MESSAGE message) {
        apiAssert.isEmpty(function.apply(this.obj), message);
        return self();
    }

    @Override
    public SELF isEmpty(MESSAGE message) {
        apiAssert.isEmpty(this.obj, message);
        return self();
    }

    @Override
    public <R> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        apiAssert.isEmpty(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    @Override
    public SELF isTrue(Function<ELEMENT, Boolean> function, MESSAGE message) {
        apiAssert.isTrue(function.apply(this.obj), message);
        return self();
    }

    @Override
    public SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message) {
        apiAssert.isTrue(function.apply(this.obj), message.apply(this.obj));
        return self();
    }

    @Override
    public SELF isFalse(Function<ELEMENT, Boolean> function, MESSAGE message) {
        apiAssert.isFalse(function.apply(this.obj), message);
        return self();
    }

    @Override
    public SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> message) {
        apiAssert.isFalse(function.apply(this.obj), message.apply(this.obj));
        return self();
    }


    @Override
    public <THENRESULT, SELF extends OptionalApiAssert<THENRESULT, SELF, MESSAGE>> SELF then(THENRESULT ELEMENT) {
        return of(ELEMENT);
    }


    @Override
    public <THENRESULT, SELF extends OptionalApiAssert<THENRESULT, SELF, MESSAGE>> SELF then(Supplier<THENRESULT> supplier) {
        return of(supplier.get());
    }


    @Override
    public <THENRESULT, SELF extends OptionalApiAssert<THENRESULT, SELF, MESSAGE>> SELF then(Function<ELEMENT, THENRESULT> function) {
        return of(function.apply(this.obj));
    }


    @Override
    public <THENRESULT, ThenS extends OptionalApiAssert<THENRESULT, ThenS, MESSAGE>> ThenS then(
            BiFunction<ELEMENT, OptionalApiAssert<ELEMENT, SELF, MESSAGE>, THENRESULT> function) {
        return of(function.apply(this.obj, self()));
    }


    @Override
    public SELF process(Consumer<ELEMENT> consumer) {
        consumer.accept(this.obj);
        return self();
    }


    @Override
    public SELF process(BiConsumer<ELEMENT, OptionalApiAssert<ELEMENT, SELF, MESSAGE>> consumer) {
        consumer.accept(this.obj, self());
        return self();
    }

    /**
     * 这里的 s 是否应该是新的，应该是新的，因为 泛型，无法 泛型《泛型》，所以需要返回新的 s，不然默认
     *
     * @param ELEMENT
     * @param <ELEMENT>
     * @return
     */
    // protected abstract <ELEMENT,  SELF extends AbstractOperationApiAssert<ELEMENT, SELF, MESSAGE>> SELF of(ELEMENT ELEMENT);
    protected abstract <ELEMENT, SELF extends OptionalApiAssert<ELEMENT, SELF, MESSAGE>> SELF of(ELEMENT ELEMENT);
}
