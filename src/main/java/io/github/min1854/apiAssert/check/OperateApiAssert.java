package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.check.abstractAssert.AbstractOperationApiAssert;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 传入对象，可以对对象与对象内部属性进行判断，如果条件成立会立即抛出异常
 *
 * @author min
 */
public class OperateApiAssert<ELEMENT> extends AbstractOperationApiAssert<ELEMENT, OperateApiAssert<ELEMENT>, String, Object> {


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


    protected <THENRESULT, THENSELF extends AbstractOperationApiAssert<THENRESULT, THENSELF, String, Object>> THENSELF of(THENRESULT thenResult) {
        // TODO 返回 null需要解决
        return null;
    }

    @Override
    public OperateApiAssert<ELEMENT> self() {
        return this;
    }

    public <ELEMENT> OperateApiAssert<ELEMENT> then(ELEMENT element) {
        return new OperateApiAssert<>(element, this.exceptionGenerator);
    }

    public <ELEMENT> OperateApiAssert<ELEMENT> then(Supplier<ELEMENT> element) {
        return new OperateApiAssert<>(element.get(), this.exceptionGenerator);
    }

    public <RESULT> OperateApiAssert<RESULT> then(Function<ELEMENT, RESULT> element) {
        return new OperateApiAssert<>(element.apply(this.obj), this.exceptionGenerator);
    }

    public <RESULT> OperateApiAssert<RESULT> then(BiFunction<ELEMENT, OperateApiAssert<ELEMENT>, RESULT> element) {
        return new OperateApiAssert<>(element.apply(this.obj, this), this.exceptionGenerator);
    }
}
