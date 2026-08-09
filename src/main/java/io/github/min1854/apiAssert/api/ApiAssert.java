package io.github.min1854.apiAssert.api;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 所有检查器的顶级接口
 *
 * @param <SELF> 实现类
 * @author min
 */
public interface ApiAssert<SELF extends ApiAssert<SELF>> {

    /**
     * 在校验过程中，需要一个处理过程或者说过度过程，不会异步执行，只会同步执行
     *
     * @param handler
     * @return
     */
    default SELF process(Runnable handler) {
        handler.run();
        return self();
    }

    /**
     * 与 {@link ApiAssert#process(Runnable)} 方法的作用相同
     *
     * @param handler
     * @return
     */
    default <V> V process(Supplier<V> handler) {
        return handler.get();
    }

    /**
     * 需要调用自身，返回 self 是为了保持链式调用，并非返回一个新的检查器
     *
     * @param handler
     * @return
     */
    default SELF apply(Consumer<SELF> handler) {
        handler.accept(self());
        return self();
    }


    /**
     * 传入自身，并需要返回一个新的检查器
     *
     * @param mapper
     * @return
     */
    default SELF map(Function<SELF, SELF> mapper) {
        return mapper.apply(self());
    }


    SELF self();

    /**
     * 校验失败，抛出指定的运行时异常
     *
     * @param exceptionSupplier 异常提供者
     */
    default void failWith(Supplier<? extends RuntimeException> exceptionSupplier) {
        throw exceptionSupplier.get();
    }

    /**
     * 校验失败，抛出指定的异常（支持受检异常）
     *
     * @param exceptionSupplier 异常提供者
     * @throws Throwable 调用者需自行处理
     */
    default void failWithThrows(Supplier<? extends Throwable> exceptionSupplier) throws Throwable {
        throw exceptionSupplier.get();
    }

    // 如果你希望保留立即抛出的便捷方法，也可以提供重载
    default void failWith(RuntimeException exception) {
        throw exception;
    }
}
