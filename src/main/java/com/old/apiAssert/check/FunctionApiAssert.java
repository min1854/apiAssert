package com.old.apiAssert.check;


import com.old.apiAssert.api.ApiAssert;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 模板，经过了测试的模板代码，其他的检查器都类似这种代码进行编写
 */
public class FunctionApiAssert implements ApiAssert<Object> {

    private ApiAssert<Object> apiAssert;
    private Function<String, RuntimeException> function;


    private FunctionApiAssert(Function<String, RuntimeException> function) {
        this.function = function;
        apiAssert = new ObjectApiAssert() {
            @Override
            protected void established(String msg) throws RuntimeException {
                throw function.apply(msg);
            }

            @Override
            protected <S extends ApiAssert<Object>> S self() {
                {
                    return (S) FunctionApiAssert.this;
                }
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
}
