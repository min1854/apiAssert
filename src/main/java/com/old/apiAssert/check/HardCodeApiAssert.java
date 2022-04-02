package com.old.apiAssert.check;


import com.old.apiAssert.exception.BaseException;

import java.util.Collection;

/**
 * 最初的版本
 * 不够通用，问题在于 exception 的 msg 只能在构造方法中赋值，无法使用 set 方法
 **/
public class HardCodeApiAssert {

    private BaseException exception;

    private HardCodeApiAssert(BaseException exception) {
        this.exception = exception;
    }

    public static HardCodeApiAssert start(BaseException exception) {
        return new HardCodeApiAssert(exception);
    }


    public HardCodeApiAssert isNull(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        return this;
    }


    public HardCodeApiAssert isEmpty(Object obj, String msg) {
        throwBaseException(obj == null, msg);
        if (obj instanceof Collection) {
            throwBaseException(((Collection<?>) obj).isEmpty(), msg);
        } else if (obj instanceof String) {
            throwBaseException(((String) obj).isEmpty(), msg);
        }
        return this;
    }

    public HardCodeApiAssert isTrue(boolean condition, String msg) {
        throwBaseException(condition, msg);
        return this;
    }

    public HardCodeApiAssert isFalse(boolean condition, String msg) {
        throwBaseException(!condition, msg);
        return this;
    }


    private void throwBaseException(boolean condition, String msg) {
        if (condition) {
            exception.setDefaultMessage(msg);
            throw exception;
        }
    }

}
