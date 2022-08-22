package com.old.apiAssert.check;

import com.old.apiAssert.check.abstractAssert.operation.AbstractOperationApiAssert;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 传入对象，可以对对象与对象内部属性进行判断，如果条件成立会立即抛出异常
 *
 * @author min
 */
public class OperateApiAssert<ELEMENT> extends AbstractOperationApiAssert<ELEMENT, OperateApiAssert<ELEMENT>, String> {


    public OperateApiAssert(ELEMENT obj, Function<String, RuntimeException> exceptionGenerator) {
        super(obj, exceptionGenerator);
    }

    public static <ELEMENT> OperateApiAssert<ELEMENT> create(ELEMENT obj, Function<String, RuntimeException> exceptionFunction) {
        return new OperateApiAssert<ELEMENT>(obj, exceptionFunction);
    }

    public static <ELEMENT> OperateApiAssert<ELEMENT> newInstance(ELEMENT obj, Supplier<RuntimeException> exceptionSupplier) {
        return create(obj, msg -> {
            throw exceptionSupplier.get();
        });
    }

    @Override
    public OperateApiAssert<ELEMENT> self() {
        return this;
    }

    @Override
    protected <THENRESULT, THENSELF extends AbstractOperationApiAssert<THENRESULT, THENSELF, String>> THENSELF of(THENRESULT thenResult) {
        return (THENSELF) new OperateApiAssert<THENRESULT>(thenResult, this.exceptionGenerator);
    }

}
