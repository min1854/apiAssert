package com.old.apiAssert.check;

import com.old.apiAssert.api.OptionalApiAssert;
import com.old.apiAssert.check.operation.AbstractOperationApiAssert;

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

    // @Override
    protected <ELEMENT, SELF extends AbstractOperationApiAssert<ELEMENT, SELF, String>> OperateApiAssert<ELEMENT> of(ELEMENT ELEMENT) {
        return new OperateApiAssert<ELEMENT>(ELEMENT, exceptionGenerator);
    }

    // @Override
    protected void established(String message) throws RuntimeException {
        throw exceptionGenerator.apply(message);
    }

    @Override
    public <ThenR, SELF extends OptionalApiAssert<ThenR, SELF, String>> SELF then(Function<ELEMENT, ThenR> function) {
        return null;
    }


   /* @Override
    public  <ELEMENT,  SELF extends AbstractOperationApiAssert<ELEMENT, OperateApiAssert<ELEMENT>, SELF, String>> SELF of(ELEMENT element) {
        return new OperateApiAssert(element, this.exceptionGenerator);
    }*/
}
