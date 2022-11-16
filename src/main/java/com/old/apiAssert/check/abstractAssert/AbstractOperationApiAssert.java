package com.old.apiAssert.check.abstractAssert;

import com.old.apiAssert.api.OptionalApiAssert;
import com.old.apiAssert.api.StandardApiAssert;
import lombok.Getter;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
public abstract class AbstractOperationApiAssert<ELEMENT extends ACTUAL, SELF extends AbstractOperationApiAssert<ELEMENT, SELF, MESSAGE, ACTUAL>, MESSAGE, ACTUAL>
        extends AbstractApiAssert<ACTUAL, SELF, MESSAGE> implements OptionalApiAssert<ELEMENT, SELF, MESSAGE, ACTUAL> {

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

    @Override
    protected void established(MESSAGE message) throws RuntimeException {
        throw exceptionGenerator.apply(message);
    }

    @Override
    public SELF nonNull(MESSAGE message) {
        return super.nonNull(this.obj, message);
    }

    @Override
    public <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, MESSAGE message) {
        return super.nonNull(function.apply(this.obj), message);
    }

    @Override
    public <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.nonNull(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public SELF isNull(MESSAGE message) {
        return super.isNull(this.obj, message);
    }

    @Override
    public <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, MESSAGE message) {
        return super.isNull(function.apply(this.obj), message);
    }

    @Override
    public <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.isNull(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, MESSAGE message) {
        return super.isEmpty(function.apply(this.obj), message);
    }

    @Override
    public SELF isEmpty(MESSAGE message) {
        return super.isEmpty(this.obj, message);
    }

    @Override
    public <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.isEmpty(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public SELF isTrue(Function<ELEMENT, Boolean> function, MESSAGE message) {
        Boolean actual = function.apply(this.obj);
        if (actual == null) {
            this.established(message);
            return self();
        }
        return super.isTrue(actual, message);
    }

    @Override
    public SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> messageCreate) {
        Boolean actual = function.apply(this.obj);
        MESSAGE message = messageCreate.apply(this.obj);
        if (actual == null) {
            this.established(message);
            return self();
        }
        return super.isTrue(actual, message);
    }

    @Override
    public SELF isFalse(Function<ELEMENT, Boolean> function, MESSAGE message) {
        Boolean actual = function.apply(this.obj);
        if (actual == null) {
            this.established(message);
            return self();
        }
        return super.isFalse(actual, message);
    }

    @Override
    public SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> messageCreate) {
        Boolean actual = function.apply(this.obj);
        MESSAGE message = messageCreate.apply(this.obj);
        if (actual == null) {
            this.established(message);
            return self();
        }
        return super.isFalse(actual, message);
    }

    @Override
    public SELF process(Consumer<ELEMENT> consumer) {
        consumer.accept(this.obj);
        return self();
    }

    @Override
    public SELF process(BiConsumer<ELEMENT, StandardApiAssert<ACTUAL, SELF, MESSAGE>> consumer) {
        consumer.accept(this.obj, this);
        return self();
    }

}
