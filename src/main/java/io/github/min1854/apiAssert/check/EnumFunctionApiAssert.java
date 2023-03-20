package io.github.min1854.apiAssert.check;


import io.github.min1854.apiAssert.check.abstractAssert.AbstractObjectApiAssert;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 异常生成由调用者提供，如果条件成立会立刻抛出指定的异常
 *
 * @author min
 */
public class EnumFunctionApiAssert<MESSAGE extends Enum<?>> extends AbstractObjectApiAssert<EnumFunctionApiAssert<MESSAGE>, MESSAGE> {


    private Function<MESSAGE, RuntimeException> exceptionGeneration;

    public EnumFunctionApiAssert(Function<MESSAGE, RuntimeException> exceptionGeneration) {
        this.exceptionGeneration = exceptionGeneration;
    }

    public static <MESSAGE extends Enum<?>> EnumFunctionApiAssert<MESSAGE> create(Function<MESSAGE, RuntimeException> function) {
        return new EnumFunctionApiAssert<MESSAGE>(function);
    }

    public static <MESSAGE extends Enum<?>> EnumFunctionApiAssert<MESSAGE> newInstance(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }

    @Override
    public EnumFunctionApiAssert<MESSAGE> self() {
        return this;
    }

    @Override
    protected void established(MESSAGE message) throws RuntimeException {
        throw exceptionGeneration.apply(message);
    }
}
