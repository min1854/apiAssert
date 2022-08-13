package com.old.apiAssert.api;


import java.util.function.Supplier;

/**
 * 所有检查器的顶级接口
 * @author min
 */
public interface ApiAssert<T, S extends ApiAssert<T, S>> {

    /**
     * 传入对象为空，则抛出异常信息
     * @param obj
     * @param msg 错误信息
     * @return
     * @throws RuntimeException
     */
    S isNull(T obj, String msg) throws RuntimeException;

    /**
     * 不为空，则抛出异常
     * @param obj
     * @param msg 异常信息
     * @return
     */
    default S nonNull(T obj, String msg) {
        return isTrue(obj != null, msg);
    }

    /**
     * 传入对象为空，则抛出异常信息
     * 包含 String。Collection、Map、数组 如果为空或者是长度为 0 都会抛出异常信息
     * @param obj
     * @param msg 错误信息
     * @return
     * @throws RuntimeException
     */
    S isEmpty(T obj, String msg) throws RuntimeException;

    /**
     *  如果条件为真，则抛出异常信息
     * @param condition 条件
     * @param msg 错误信息
     * @return
     * @throws RuntimeException
     */
    S isTrue(boolean condition, String msg) throws RuntimeException;

    /**
     *  如果条件为假，则抛出异常信息
     * @param condition 条件
     * @param msg 错误信息
     * @return
     * @throws RuntimeException
     */
    S isFalse(boolean condition, String msg) throws RuntimeException;

    /**
     * 在校验过程中，需要一个处理过程或者说过度过程，不会异步执行，只会同步执行
     * @param handler
     * @return
     */
    S process(Runnable handler);

    /**
     * 与 {@link ApiAssert#process(Runnable)} 方法的作用相同
     * @param handler
     * @return
     */
    default <V> V process(Supplier<V> handler) {
        return handler.get();
    }


    S self();

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
