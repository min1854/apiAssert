package com.old.apiAssert.check;


import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 异常生成由调用者提供，如果条件成立会立刻抛出指定的异常
 *
 * @author min
 */
public class FunctionApiAssert extends ObjectApiAssert<FunctionApiAssert> {

    private Function<String, RuntimeException> function;


    public FunctionApiAssert(Function<String, RuntimeException> function) {
        this.function = function;
    }

    public static FunctionApiAssert create(Function<String, RuntimeException> function) {
        return new FunctionApiAssert(function);
    }

    public static FunctionApiAssert newInstance(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }

    @Override
    public FunctionApiAssert self() {
        return this;
    }

    @Override
    protected void established(String msg) throws RuntimeException {
        throw function.apply(msg);
    }
}
