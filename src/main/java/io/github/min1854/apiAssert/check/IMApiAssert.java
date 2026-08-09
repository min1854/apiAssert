package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.check.abstractAssert.AbstractApiAssert;
import io.github.min1854.apiAssert.contracts.IM;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * IM 断言检查器，以 {@link IM} 接口作为消息载体，支持自定义结果码和结果描述。<br>
 * 适用于需要统一错误码管理的场景，可配合枚举实现 {@link IM} 接口使用。<br>
 * 如果条件成立会立刻抛出指定的异常。<br>
 * <br>
 * 使用示例：
 * <pre>{@code
 * IMApiAssert apiAssert = IMApiAssert.create(IMException::new);
 * apiAssert.isNull(obj, IMEnum.FAIL.format("对象不能为空"))
 *         .isTrue(flag, IMEnum.FAIL.format("条件为真"));
 * }</pre>
 *
 * @author min
 * @see IM
 * @see IRApiAssert
 */
public class IMApiAssert extends AbstractApiAssert<Object, IMApiAssert, IM> {


    private final Function<IM, RuntimeException> exceptionGeneration;

    /**
     * 构造一个 IMApiAssert 实例
     *
     * @param exceptionGeneration 将 {@link IM} 消息转换为运行时异常的函数
     */
    public IMApiAssert(Function<IM, RuntimeException> exceptionGeneration) {
        this.exceptionGeneration = exceptionGeneration;
    }

    /**
     * 创建一个 IMApiAssert 实例
     *
     * @param function 将 {@link IM} 消息转换为运行时异常的函数
     * @return IMApiAssert 实例
     */
    public static IMApiAssert create(Function<IM, RuntimeException> function) {
        return new IMApiAssert(function);
    }

    /**
     * 创建一个 IMApiAssert 实例，忽略消息内容，直接使用提供的异常
     *
     * @param supplier 运行时异常提供者
     * @return IMApiAssert 实例
     */
    public static IMApiAssert newInstance(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }

    @Override
    protected void established(Supplier<IM> message) throws RuntimeException {
        throw exceptionGeneration.apply(message.get());
    }

    @Override
    public IMApiAssert self() {
        return this;
    }
}
