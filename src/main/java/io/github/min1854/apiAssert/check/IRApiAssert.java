package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.check.abstractAssert.AbstractApiAssert;
import io.github.min1854.apiAssert.contracts.IR;

import java.util.function.Function;
import java.util.function.Supplier;


/**
 * IR 断言检查器，以 {@link IR} 接口作为消息载体，在 {@link IM} 的基础上额外支持结果数据。<br>
 * 适用于需要统一错误码管理且携带业务数据的场景，可配合枚举实现 {@link IR} 接口使用。<br>
 * 如果条件成立会立刻抛出指定的异常。<br>
 * <br>
 * 使用示例：
 * <pre>{@code
 * IRApiAssert apiAssert = IRApiAssert.create(IRException::new);
 * apiAssert.isNull(obj, IREnum.FAIL.format("对象不能为空"))
 *         .isTrue(flag, IREnum.FAIL.format("条件为真"));
 * }</pre>
 *
 * @author min
 * @see IR
 * @see IMApiAssert
 */
public class IRApiAssert extends AbstractApiAssert<Object, IRApiAssert, IR> {


    private final Function<IR, RuntimeException> exceptionGeneration;

    /**
     * 构造一个 IRApiAssert 实例
     *
     * @param exceptionGeneration 将 {@link IR} 消息转换为运行时异常的函数
     */
    public IRApiAssert(Function<IR, RuntimeException> exceptionGeneration) {
        this.exceptionGeneration = exceptionGeneration;
    }

    /**
     * 创建一个 IRApiAssert 实例
     *
     * @param function 将 {@link IR} 消息转换为运行时异常的函数
     * @return IRApiAssert 实例
     */
    public static IRApiAssert create(Function<IR, RuntimeException> function) {
        return new IRApiAssert(function);
    }

    /**
     * 创建一个 IRApiAssert 实例，忽略消息内容，直接使用提供的异常
     *
     * @param supplier 运行时异常提供者
     * @return IRApiAssert 实例
     */
    public static IRApiAssert newInstance(Supplier<RuntimeException> supplier) {
        return create(msg -> supplier.get());
    }

    @Override
    protected void established(Supplier<IR> message) throws RuntimeException {
        throw exceptionGeneration.apply(message.get());
    }

    @Override
    public IRApiAssert self() {
        return this;
    }
}
