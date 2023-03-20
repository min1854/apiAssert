package io.github.min1854.apiAssert.api;


import java.util.function.Supplier;

/**
 * 所有检查器的顶级接口
 *
 * @param <SELF>    实现类
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


    SELF self();

    default void throwThrowable(Supplier<Throwable> throwable) throws Throwable {
        throw throwable.get();
    }

    default void throwThrowable(Throwable throwable) throws Throwable {
        throw throwable;
    }

    default void throwRuntime(Supplier<RuntimeException> exception) throws RuntimeException {
        throw exception.get();
    }

    default void throwRuntime(RuntimeException exception) throws RuntimeException {
        throw exception;
    }
}
