package com.old.check;

import com.old.Holder;
import lombok.Data;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 第4个版本
 */
@Data
public class FirstApiAssert {

    private String errorMsg;


    public static FirstApiAssert start() {
        return new FirstApiAssert();
    }


    public FirstApiAssert isNull(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        return this;
    }


    public FirstApiAssert isEmpty(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        if (obj instanceof Collection) {
            throwBaseException(((Collection<?>) obj).isEmpty(), msg);
        } else if (obj instanceof String) {
            throwBaseException(((String) obj).isEmpty(), msg);
        }
        return this;
    }

    public FirstApiAssert isTrue(boolean condition, String msg) {
        throwBaseException(condition, msg);
        return this;
    }

    public FirstApiAssert isFalse(boolean condition, String msg) {
        throwBaseException(!condition, msg);
        return this;
    }

    public boolean isSuccess() {
        return errorMsg == null;
    }

    public FirstApiAssert hodler(Holder holder) {
        holder.setFlag(isSuccess());
        return this;
    }


    /**
     * isSuccess 和 fail 不是这样的，应该是先判断状态，状态对才这样，
     * 再加一个 handler 方法，参数是 Consumer<Boolean> consumer 这样才对
     *
     * @param consumer
     * @return
     */
    public FirstApiAssert isSuccess(Consumer<Boolean> consumer) {
        consumer.accept(isSuccess());
        return this;
    }

    public FirstApiAssert isFail(Consumer<Boolean> consumer) {
        consumer.accept(isFail());
        return this;
    }

    public FirstApiAssert handler(Consumer<Boolean> consumer) {
        consumer.accept(isSuccess());
        return this;
    }


    public FirstApiAssert isSuccess(Runnable commmand) {
        if (isSuccess()) {
            commmand.run();
        }
        return this;
    }

    public FirstApiAssert isFail(Runnable commmand) {
        if (isFail()) {
            commmand.run();
        }
        return this;
    }


    public boolean isFail() {
        return !isSuccess();
    }

    public void throwRunTime(Function<String, RuntimeException> e) {
        throw e.apply(errorMsg);
    }

    public void throwThrowable(Function<String, Throwable> e) throws Throwable {
        throw e.apply(errorMsg);
    }

    public void failThrow(Function<String, RuntimeException> e) {
        if (isFail()) {
            throw e.apply(errorMsg);
        }
    }

    public void failThrowable(Function<String, Throwable> e) throws Throwable {
        if (isFail()) {
            throw e.apply(errorMsg);
        }
    }


    private void throwBaseException(boolean condition, String msg) {
        if (condition && this.errorMsg == null) {
            this.errorMsg = msg;
        }
    }

}
