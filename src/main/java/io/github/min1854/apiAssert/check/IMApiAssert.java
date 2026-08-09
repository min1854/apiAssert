package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.check.abstractAssert.AbstractApiAssert;
import io.github.min1854.apiAssert.contracts.IM;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * IM断言，以{@link io.github.min1854.apiAssert.contracts.IM}接口作为消息，
 */
public class IMApiAssert extends AbstractApiAssert<Object, IMApiAssert, IM> {


    private final Function<IM, RuntimeException> exceptionGeneration;

    public IMApiAssert(Function<IM, RuntimeException> exceptionGeneration) {
        this.exceptionGeneration = exceptionGeneration;
    }

    public static IMApiAssert create(Function<IM, RuntimeException> function) {
        return new IMApiAssert(function);
    }

    public static IMApiAssert newInstance(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }

    @Override
    protected void established(Supplier<IM> message) throws RuntimeException {
        throw exceptionGeneration.apply(message.get());
    }

    @Override
    public IMApiAssert self() {
        return this;
    }
}
