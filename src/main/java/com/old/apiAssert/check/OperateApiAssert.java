package com.old.apiAssert.check;

import com.old.apiAssert.api.StandardApiAssert;
import com.old.apiAssert.check.abstractAssert.operation.AbstractOperationApiAssert;

import java.util.function.BiFunction;
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

    protected <THENRESULT, THENSELF extends AbstractOperationApiAssert<THENRESULT, THENSELF, String>> THENSELF of(THENRESULT thenResult) {
        return null;
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

    // protected <THENRESULT, THENSELF extends AbstractOperationApiAssert<THENRESULT, THENSELF, String>> THENSELF of(THENRESULT thenResult) {
    //     return (THENSELF) new OperateApiAssert<THENRESULT>(thenResult, this.exceptionGenerator);
    // }

    /*public <THENRESULT, MESSAGE, THENSELF extends OptionalApiAssert<THENRESULT, THENSELF, MESSAGE>> OperateApiAssert<THENRESULT> then(THENRESULT thenResult) {
        return new OperateApiAssert<THENRESULT>(thenResult, this.exceptionGenerator);
    }*/

    public <ELEMENT> OperateApiAssert<ELEMENT> then(ELEMENT element) {
        return new OperateApiAssert<>(element, this.exceptionGenerator);
    }

    public <ELEMENT> OperateApiAssert<ELEMENT> then(Supplier<ELEMENT> element) {
        return new OperateApiAssert<>(element.get(), this.exceptionGenerator);
    }

    public <RESULT> OperateApiAssert<RESULT> then(Function<ELEMENT, RESULT> element) {
        return new OperateApiAssert<>(element.apply(this.obj), this.exceptionGenerator);
    }

    public <RESULT> OperateApiAssert<RESULT> process(Function<StandardApiAssert<Object, OperateApiAssert<ELEMENT>, String>, RESULT> element) {
        return new OperateApiAssert<>(element.apply(this), this.exceptionGenerator);
    }

    public <RESULT> OperateApiAssert<RESULT> then(BiFunction<ELEMENT, StandardApiAssert<Object, OperateApiAssert<ELEMENT>, String>, RESULT> element) {
        return new OperateApiAssert<>(element.apply(this.obj, this), this.exceptionGenerator);
    }
}
