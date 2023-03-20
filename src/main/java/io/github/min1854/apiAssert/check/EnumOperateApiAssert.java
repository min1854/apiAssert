package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.api.StandardApiAssert;
import io.github.min1854.apiAssert.check.abstractAssert.AbstractOperationApiAssert;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 传入对象，可以对对象与对象内部属性进行判断，如果条件成立会立即抛出异常
 *
 * @author min
 */
public class EnumOperateApiAssert<ELEMENT, MESSAGE extends Enum<?>>
        extends AbstractOperationApiAssert<ELEMENT, EnumOperateApiAssert<ELEMENT, MESSAGE>, MESSAGE, Object> {


    public EnumOperateApiAssert(ELEMENT obj, Function<MESSAGE, RuntimeException> exceptionGenerator) {
        super(obj, exceptionGenerator);
    }

    public static <ELEMENT, MESSAGE extends Enum<?>> EnumOperateApiAssert<ELEMENT, MESSAGE> create(ELEMENT obj, Function<MESSAGE, RuntimeException> exceptionFunction) {
        return new EnumOperateApiAssert<ELEMENT, MESSAGE>(obj, exceptionFunction);
    }

    public static <ELEMENT, MESSAGE extends Enum<?>> EnumOperateApiAssert<ELEMENT, MESSAGE> newInstance(ELEMENT obj, Supplier<RuntimeException> exceptionSupplier) {
        return create(obj, msg -> {
            throw exceptionSupplier.get();
        });
    }

    @Override
    public EnumOperateApiAssert<ELEMENT, MESSAGE> self() {
        return this;
    }

    public <MESSAGE extends Enum<?>> EnumOperateApiAssert<ELEMENT, MESSAGE> replace(Function<MESSAGE, RuntimeException> exceptionGenerator) {
        return new EnumOperateApiAssert<>(this.obj, exceptionGenerator);
    }

    public <ELEMENT> EnumOperateApiAssert<ELEMENT, MESSAGE> then(ELEMENT element) {
        return new EnumOperateApiAssert<>(element, this.exceptionGenerator);
    }

    public <ELEMENT> EnumOperateApiAssert<ELEMENT, MESSAGE> then(Supplier<ELEMENT> element) {
        return new EnumOperateApiAssert<>(element.get(), this.exceptionGenerator);
    }

    public <RESULT> EnumOperateApiAssert<RESULT, MESSAGE> then(Function<ELEMENT, RESULT> element) {
        return new EnumOperateApiAssert<>(element.apply(this.obj), this.exceptionGenerator);
    }

    public <RESULT> EnumOperateApiAssert<RESULT, MESSAGE> then(BiFunction<ELEMENT, StandardApiAssert<Object, EnumOperateApiAssert<ELEMENT, MESSAGE>, MESSAGE>, RESULT> element) {
        return new EnumOperateApiAssert<>(element.apply(this.obj, this), this.exceptionGenerator);
    }
}
