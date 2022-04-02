package com.old.check;


import com.old.api.ApiAssert;

import java.util.function.Function;

public class TestCodeAssert<T> implements ApiAssert<T> {

    private InternalAssert internalAssert;


    private static class InternalAssert extends AbstractApiAssert {
        private Function<String, RuntimeException> function;

        public InternalAssert(Function<String, RuntimeException> function) {
            this.function = function;
        }

        @Override
        protected void judge(boolean condition, String msg) {
            if (condition) {
                throw this.function.apply(msg);
            }
        }
    }

    private TestCodeAssert(Function<String, RuntimeException> function){
        internalAssert = new InternalAssert(function);
    }

    public static ApiAssert create(Function<String, RuntimeException> function) {
        return new TestCodeAssert(function);
    }


    @Override
    public ApiAssert<T> isNull(T obj, String msg) {
        return internalAssert.isNull(obj, msg);
    }

    @Override
    public ApiAssert<T> isEmpty(T obj, String msg) {
        return internalAssert.isEmpty(obj, msg);
    }

    @Override
    public ApiAssert<T> isTrue(boolean condition, String msg) {
        return internalAssert.isTrue(condition, msg);
    }

    @Override
    public ApiAssert<T> isFalse(boolean condition, String msg) {
        return internalAssert.isFalse(condition, msg);
    }
}
