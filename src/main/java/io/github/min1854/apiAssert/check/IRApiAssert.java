package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.check.abstractAssert.AbstractApiAssert;
import io.github.min1854.apiAssert.contracts.IR;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * IR断言，以{@link io.github.min1854.apiAssert.contracts.IR}接口作为消息，
 */
public class IRApiAssert extends AbstractApiAssert<Object, IRApiAssert, IR> {


    private final Function<IR, RuntimeException> exceptionGeneration;

    public IRApiAssert(Function<IR, RuntimeException> exceptionGeneration) {
        this.exceptionGeneration = exceptionGeneration;
    }

    public static IRApiAssert create(Function<IR, RuntimeException> function) {
        return new IRApiAssert(function);
    }

    public static IRApiAssert newInstance(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }

    @Override
    protected void established(Supplier<IR> message) throws RuntimeException {
        throw exceptionGeneration.apply(message.get());
    }

    @Override
    public IRApiAssert self() {
        return this;
    }
}
