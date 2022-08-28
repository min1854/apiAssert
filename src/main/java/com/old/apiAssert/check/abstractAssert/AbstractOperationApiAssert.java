package com.old.apiAssert.check.abstractAssert;

import com.old.apiAssert.api.OptionalApiAssert;
import com.old.apiAssert.api.StandardApiAssert;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractOperationApiAssert<ELEMENT, SELF extends AbstractOperationApiAssert<ELEMENT, SELF, MESSAGE>, MESSAGE>
        extends AbstractObjectApiAssert<SELF, MESSAGE> implements OptionalApiAssert<ELEMENT, SELF, MESSAGE, Object> {

    protected ELEMENT obj;

    protected Function<MESSAGE, RuntimeException> exceptionGenerator;

    public AbstractOperationApiAssert(ELEMENT obj, Function<MESSAGE, RuntimeException> exceptionGenerator) {
        this.obj = obj;
        this.exceptionGenerator = exceptionGenerator;
    }

    public SELF setExceptionGenerator(Function<MESSAGE, RuntimeException> exceptionGenerator) {
        this.exceptionGenerator = exceptionGenerator;
        return self();
    }


    public ELEMENT getObj() {
        return obj;
    }

    @Override
    protected void established(MESSAGE message) throws RuntimeException {
        throw exceptionGenerator.apply(message);
    }

    @Override
    public SELF nonNull(MESSAGE message) {
        return super.nonNull(this.obj, message);
    }

    @Override
    public <R> SELF nonNull(Function<ELEMENT, R> function, MESSAGE message) {
        return super.nonNull(function.apply(this.obj), message);
    }

    @Override
    public <R> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.nonNull(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public SELF isNull(MESSAGE message) {
        return super.isNull(this.obj, message);
    }

    @Override
    public <R> SELF isNull(Function<ELEMENT, R> function, MESSAGE message) {
        return super.isNull(function.apply(this.obj), message);
    }

    @Override
    public <R> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.isNull(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public <R> SELF isEmpty(Function<ELEMENT, R> function, MESSAGE message) {
        return super.isEmpty(function.apply(this.obj), message);
    }

    @Override
    public SELF isEmpty(MESSAGE message) {
        return super.isEmpty(this.obj, message);
    }

    @Override
    public <R> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.isEmpty(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public SELF isTrue(Function<ELEMENT, Boolean> function, MESSAGE message) {
        Boolean actual = function.apply(this.obj);
        super.isNull(actual, message);
        return super.isTrue(actual, message);
    }

    @Override
    public SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> messageCreate) {
        Boolean actual = function.apply(this.obj);
        MESSAGE message = messageCreate.apply(this.obj);
        super.isNull(actual, message);
        return super.isTrue(actual, message);
    }

    @Override
    public SELF isFalse(Function<ELEMENT, Boolean> function, MESSAGE message) {
        Boolean actual = function.apply(this.obj);
        super.isNull(actual, message);
        return super.isFalse(actual, message);
    }

    @Override
    public SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> messageCreate) {
        Boolean actual = function.apply(this.obj);
        MESSAGE message = messageCreate.apply(this.obj);
        super.isNull(actual, message);
        return super.isFalse(actual, message);
    }

    @Override
    public SELF process(Consumer<ELEMENT> consumer) {
        consumer.accept(this.obj);
        return self();
    }

    @Override
    public SELF process(BiConsumer<ELEMENT, StandardApiAssert<java.lang.Object, SELF, MESSAGE>> consumer) {
        consumer.accept(this.obj, this);
        return self();
    }

}
