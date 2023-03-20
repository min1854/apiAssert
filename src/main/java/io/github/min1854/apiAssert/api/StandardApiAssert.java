package io.github.min1854.apiAssert.api;


import java.util.function.Supplier;

public interface StandardApiAssert<ACTUAL, SELF extends StandardApiAssert<ACTUAL, SELF, MESSAGE>, MESSAGE> extends ApiAssert<SELF> {

    /**
     * 传入对象为空，则抛出异常信息
     *
     * @param obj
     * @param message 错误信息
     * @return
     * @throws RuntimeException
     */
    SELF isNull(ACTUAL obj, MESSAGE message) throws RuntimeException;

    default SELF isNull(ACTUAL obj, Supplier<MESSAGE> message) throws RuntimeException {
        isNull(obj, message.get());
        return self();
    }

    /**
     * 不为空，则抛出异常
     *
     * @param obj
     * @param message 异常信息
     * @return
     */
    default SELF nonNull(ACTUAL obj, MESSAGE message) {
        return isTrue(obj != null, message);
    }

    default SELF nonNull(ACTUAL obj, Supplier<MESSAGE> message) throws RuntimeException {
        nonNull(obj, message.get());
        return self();
    }

    /**
     * 传入对象为空，则抛出异常信息
     * 包含 String。Collection、Map、数组 如果为空或者是长度为 0 都会抛出异常信息
     *
     * @param obj
     * @param message 错误信息
     * @return
     * @throws RuntimeException
     */
    SELF isEmpty(ACTUAL obj, MESSAGE message) throws RuntimeException;

    default SELF isEmpty(ACTUAL obj, Supplier<MESSAGE> message) throws RuntimeException {
        isEmpty(obj, message.get());
        return self();
    }

    /**
     * 如果条件为真，则抛出异常信息
     *
     * @param condition 条件
     * @param message   错误信息
     * @return
     * @throws RuntimeException
     */
    SELF isTrue(boolean condition, MESSAGE message) throws RuntimeException;

    default SELF isTrue(boolean condition, Supplier<MESSAGE> message) throws RuntimeException {
        isTrue(condition, message.get());
        return self();
    }

    /**
     * 如果条件为假，则抛出异常信息
     *
     * @param condition 条件
     * @param message   错误信息
     * @return
     * @throws RuntimeException
     */
    SELF isFalse(boolean condition, MESSAGE message) throws RuntimeException;

    default SELF isFalse(boolean condition, Supplier<MESSAGE> message) throws RuntimeException {
        isFalse(condition, message.get());
        return self();
    }

    /**
     * 默认空实现
     * @param message
     * @return
     */
    default public SELF handler(MESSAGE message) {
        return self();
    }

}
