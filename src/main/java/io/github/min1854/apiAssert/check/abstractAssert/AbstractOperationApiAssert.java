package io.github.min1854.apiAssert.check.abstractAssert;

import io.github.min1854.apiAssert.api.OptionalApiAssert;
import io.github.min1854.apiAssert.exception.ApiAssertException;
import lombok.Getter;

import java.util.function.Function;
import java.util.function.Supplier;

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
    public <R extends ACTUAL> SELF isNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.isNull(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public <R extends ACTUAL> SELF nonNull(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.nonNull(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public <R extends ACTUAL> SELF isEmpty(Function<ELEMENT, R> function, Function<ELEMENT, MESSAGE> message) {
        return super.isEmpty(function.apply(this.obj), message.apply(this.obj));
    }

    @Override
    public SELF isTrue(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> messageCreate) {
        Boolean actual = function.apply(this.obj);
        if (actual == null) {
            throw new ApiAssertException("返回结果不能为空");
        }
        return super.isTrue(actual, messageCreate.apply(this.obj));
    }

    @Override
    public SELF isFalse(Function<ELEMENT, Boolean> function, Function<ELEMENT, MESSAGE> messageCreate) {
        Boolean actual = function.apply(this.obj);
        if (actual == null) {
            throw new ApiAssertException("返回结果不能为空");
        }
        return super.isFalse(actual, messageCreate.apply(this.obj));
    }

    @Override
    public ELEMENT get() {
        return this.obj;
    }

    @Override
    protected void established(Supplier<MESSAGE> message) throws RuntimeException {
        throw exceptionGenerator.apply(message.get());
    }

}
