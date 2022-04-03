package com.old.apiAssert.check;


import com.old.apiAssert.api.ApiAssert;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 模板，经过了测试的模板代码，其他的检查器都类似这种代码进行编写
 */
public class TestCodeAssert implements ApiAssert<Object> {

    private InternalAssert internalAssert;


    private class InternalAssert extends AbstractApiAssert<Object> {
        private Function<String, RuntimeException> function;

        public InternalAssert(Function<String, RuntimeException> function) {
            this.function = function;
        }

        @Override
        protected void established(String msg) {
            throw function.apply(msg);
        }

        @Override
        protected <S extends ApiAssert<Object>> S self() {
            return (S) TestCodeAssert.this;
        }
    }


    private TestCodeAssert(Function<String, RuntimeException> function) {
        internalAssert = new InternalAssert(function);
    }

    public static ApiAssert<Object> create(Function<String, RuntimeException> function) {
        return new TestCodeAssert(function);
    }

    public static ApiAssert<Object> create(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }

    public static ApiAssert<Object> newInstance(Supplier<RuntimeException> supplier) {
        return create(supplier);
    }


    @Override
    public ApiAssert<Object> isNull(Object obj, String msg) {
        return internalAssert.isNull(obj, msg);
    }

    @Override
    public ApiAssert<Object> isEmpty(Object obj, String msg) {
        return internalAssert.isEmpty(obj, msg);
    }

    @Override
    public ApiAssert<Object> isTrue(boolean condition, String msg) {
        return internalAssert.isTrue(condition, msg);
    }

    @Override
    public ApiAssert<Object> isFalse(boolean condition, String msg) {
        return internalAssert.isFalse(condition, msg);
    }
}
