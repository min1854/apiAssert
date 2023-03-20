package io.github.min1854.apiAssert.check;

import io.github.min1854.apiAssert.tuple.Holder;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 当第一个条件成立就会保存异常信息，不会再替换异常信息，异常由最终方法决定是否抛出，不会自主抛出，由调用者决定
 *
 * @author min
 */
public class FirstApiAssert extends DefaultApiAssert<FirstApiAssert> {

    private String errorMsg;


    public static FirstApiAssert create() {
        return new FirstApiAssert();
    }

    public FirstApiAssert() {
    }

    @Override
    protected void established(String msg) throws RuntimeException {
        if (errorMsg == null) {
            errorMsg = msg;
        }
    }

    @Override
    public FirstApiAssert self() {
        return this;
    }

    /**
     * 当前是否是成功的状态
     *
     * @return
     */
    public boolean isSuccess() {
        return errorMsg == null;
    }

    /**
     * 持有当前状态
     *
     * @param holder
     * @return
     */
    public FirstApiAssert holder(Holder<Boolean> holder) {
        holder.setData(isSuccess());
        return this;
    }

    /**
     * @param consumer 对于当前状态是否成功，的处理
     * @return
     */
    public FirstApiAssert handler(Consumer<Boolean> consumer) {
        consumer.accept(isSuccess());
        return this;
    }


    /**
     * @param consumer 对于当前状态是否成功，的处理
     * @return
     */
    public FirstApiAssert handler(BiConsumer<Boolean, String> consumer) {
        consumer.accept(isSuccess(), errorMsg);
        return this;
    }


    /**
     * 如果是成功的处理，不会异步执行，而是同步，
     *
     * @param command
     * @return
     */
    public FirstApiAssert ifSuccess(Runnable command) {
        if (isSuccess()) {
            command.run();
        }
        return this;
    }

    /**
     * 如果是失败的处理，不会异步执行，而是同步，
     *
     * @param command
     * @return
     */
    public FirstApiAssert ifFail(Runnable command) {
        if (isFail()) {
            command.run();
        }
        return this;
    }


    /**
     * 是否是失败的状态
     *
     * @return
     */
    public boolean isFail() {
        return !isSuccess();
    }

    /**
     * 抛出运行时间异常
     *
     * @param e
     */
    public void throwRunTime(Function<String, RuntimeException> e) {
        throw e.apply(errorMsg);
    }

    /**
     * 抛出异常
     *
     * @param e
     */
    public void throwThrowable(Function<String, Throwable> e) throws Throwable {
        throw e.apply(errorMsg);
    }

    /**
     * 如果失败则抛出运行时间异常
     *
     * @param e
     */
    public void failThrow(Function<String, RuntimeException> e) {
        if (isFail()) {
            throw e.apply(errorMsg);
        }
    }

    /**
     * 如果失败则抛出异常
     *
     * @param e
     */
    public void failThrowable(Function<String, Throwable> e) throws Throwable {
        if (isFail()) {
            throw e.apply(errorMsg);
        }
    }
}
