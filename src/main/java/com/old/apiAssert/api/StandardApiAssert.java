package com.old.apiAssert.api;


public interface StandardApiAssert<T, SELF extends StandardApiAssert<T, SELF, MESSAGE>, MESSAGE> extends ApiAssert<SELF> {

    /**
     * 传入对象为空，则抛出异常信息
     *
     * @param obj
     * @param message 错误信息
     * @return
     * @throws RuntimeException
     */
    SELF isNull(T obj, MESSAGE message) throws RuntimeException;

    /**
     * 不为空，则抛出异常
     *
     * @param obj
     * @param message 异常信息
     * @return
     */
    default SELF nonNull(T obj, MESSAGE message) {
        return isTrue(obj != null, message);
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
    SELF isEmpty(T obj, MESSAGE message) throws RuntimeException;

    /**
     * 如果条件为真，则抛出异常信息
     *
     * @param condition 条件
     * @param message   错误信息
     * @return
     * @throws RuntimeException
     */
    SELF isTrue(boolean condition, MESSAGE message) throws RuntimeException;

    /**
     * 如果条件为假，则抛出异常信息
     *
     * @param condition 条件
     * @param message   错误信息
     * @return
     * @throws RuntimeException
     */
    SELF isFalse(boolean condition, MESSAGE message) throws RuntimeException;

}
