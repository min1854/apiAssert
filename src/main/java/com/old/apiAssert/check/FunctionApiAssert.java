package com.old.apiAssert.check;


import com.old.apiAssert.api.ApiAssert;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 异常生成由调用者提供，如果条件成立会立刻抛出指定的异常
 * @author min
 */
public class FunctionApiAssert implements ApiAssert<Object> {

    private ApiAssert<Object> apiAssert;
    private Function<String, RuntimeException> function;


    public FunctionApiAssert(Function<String, RuntimeException> function) {
        this.function = function;
        apiAssert = new ObjectApiAssert() {
            @Override
            protected void established(String msg) throws RuntimeException {
                throw function.apply(msg);
            }

            @Override
            protected <S extends ApiAssert<Object>> S self() {
                    return (S) FunctionApiAssert.this;
            }
        };
    }

    public static ApiAssert<Object> create(Function<String, RuntimeException> function) {
        return new FunctionApiAssert(function);
    }

    public static ApiAssert<Object> newInstance(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }


    @Override
    public ApiAssert<Object> isNull(Object obj, String msg) {
        return apiAssert.isNull(obj, msg);
    }

    @Override
    public ApiAssert<Object> isEmpty(Object obj, String msg) {
        return apiAssert.isEmpty(obj, msg);
    }

    @Override
    public ApiAssert<Object> isTrue(boolean condition, String msg) {
        return apiAssert.isTrue(condition, msg);
    }

    @Override
    public ApiAssert<Object> isFalse(boolean condition, String msg) {
        return apiAssert.isFalse(condition, msg);
    }

    @Override
    public ApiAssert<Object> process(Runnable handler) {
        return apiAssert.process(handler);
    }
}
